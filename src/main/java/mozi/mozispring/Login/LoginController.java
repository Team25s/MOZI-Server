package mozi.mozispring.Login;

import com.google.cloud.storage.StorageException;
import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Comment.CommentRepository;
import mozi.mozispring.Domain.Dto.*;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Jwt.JwtTokenProvider;
import mozi.mozispring.Moment.MomentRepository;
import mozi.mozispring.Firebase.FireBaseService;
import mozi.mozispring.Schedule.ScheduleRepository;
import mozi.mozispring.User.SimplUserRepository;
import mozi.mozispring.User.UserRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final MomentRepository momentRepository;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final FireBaseService fireBaseService;
    private final SimplUserRepository simplUserRepository;

    @Autowired
    public LoginController(
            PasswordEncoder passwordEncoder
            , JwtTokenProvider jwtTokenProvider
            , UserRepository userRepository
            , MomentRepository momentRepository
            , CommentRepository commentRepository
            , ScheduleRepository scheduleRepository
            , FireBaseService fireBaseService
            , SimplUserRepository simplUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.momentRepository = momentRepository;
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.fireBaseService = fireBaseService;
        this.simplUserRepository = simplUserRepository;
    }

    /**
     * 회원가입 api
     */
    @ApiOperation(value="회원가입하기", notes="회원가입하기")
    @PostMapping("/join")
    @ResponseBody
    public JoinDto joinController(@RequestBody SignInDto signInDto) {
        System.out.println("회원가입 요청입니다.");
        Optional<User> result = userRepository.findByEmail(signInDto.getEmail());
        if(result.isPresent()){
            JoinDto joinDto = new JoinDto();
            joinDto.setMessage("이미 존재하는 회원입니다.");
            joinDto.setUserId(-1L);
            return joinDto;
        }else {
            User user = User.builder()
                    .email(signInDto.getEmail())
                    .password(passwordEncoder.encode(signInDto.getPassword()))
                    .name(signInDto.getName())
                    .build();
            userRepository.save(user);
            SimplUser simplUser = SimplUser.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .mbti(user.getMbti())
                    .profileFilename(null)
                    .profileFileURL(null)
                    .build();
            JoinDto joinDto = new JoinDto();
            joinDto.setUserId(simplUserRepository.save(simplUser).getId());
            joinDto.setMessage("회원가입에 성공하였습니다.");
            return joinDto;
        }
    }

    /**
     * 로그인 api
     */
    @ApiOperation(value="로그인하기 ", notes="로그인하기")
    @PostMapping("/login")
    @ResponseBody
    public JwtRetDto loginController(@RequestBody LogInDto logInDto) {
        System.out.println("로그인 요청입니다.");
        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());
        if(!findUser.isPresent()){
            JwtRetDto jwtRetDto = new JwtRetDto();
            jwtRetDto.setMessage("가입되지 않은 아이디입니다.");
            jwtRetDto.setJwt(null);
            return jwtRetDto;
        }
        User member = findUser.get();
        if (!passwordEncoder.matches(logInDto.getPassword(), member.getPassword())) {
            JwtRetDto jwtRetDto = new JwtRetDto();
            jwtRetDto.setMessage("잘못된 비밀번호입니다.");
            jwtRetDto.setJwt(null);
            return jwtRetDto;
        }
        JwtRetDto jwtRetDto = new JwtRetDto();
        jwtRetDto.setMessage("유효한 jwt를 발급하였습니다.");
        jwtRetDto.setJwt(jwtTokenProvider.createToken(member.getEmail()));
        return jwtRetDto;
    }

    /**
     * 회원탈퇴 api
     */
    @ApiOperation(value="회원탈퇴하기 ", notes="회원탈퇴하기")
    @PostMapping("/withdraw")
    @ResponseBody
    public DeleteDto withdrawController(@RequestBody LogInDto logInDto){
        System.out.println("1. 회원탈퇴 요청입니다. ");

        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());

        DeleteDto deleteDto = new DeleteDto();
        if(!findUser.isPresent()){
            System.out.println("2. 탈퇴하려는 계정이 존재하지 않습니다.");
            // return ResponseEntity.ok().body(new ErrorResponse("탈퇴하려는 계정이 존재하지 않습니다."));
            deleteDto.setDeleted(false);
            deleteDto.setMessage("탈퇴하려는 계정이 존재하지 않습니다. ");
            return deleteDto;
        }

        if(passwordEncoder.matches(logInDto.getPassword(), findUser.get().getPassword())) {
            if (findUser.get().getProfileFilename() != null){
                try {
                    fireBaseService.deleteFiles(findUser.get().getProfileFilename()); // 파이어베이스에서 프로필 이미지 삭제
                    System.out.println("3. 파이어베이스에서 이미지를 삭제하고 있습니다.");
                }catch(StorageException e){
                    System.out.println("4. 이미지 삭제 도중 예외가 발생했습니다. ");
                    System.out.println(e.getMessage());
                }
            }
            userRepository.deleteById(findUser.get().getId());                // 회원 정보 디비에서 삭제
            simplUserRepository.deleteByEmail(findUser.get().getEmail());     // 회원 요약 정보 디비에서 삭제

            System.out.println("5. 디비에서 회원을 삭제하였습니다.");
            deleteDto.setDeleted(true);
            deleteDto.setMessage("성공적으로 탈퇴되었습니다.");
            return deleteDto;
        }else{
            System.out.println("6. 비밀번호가 맞지 않습니다.");
            deleteDto.setDeleted(false);
            deleteDto.setMessage("비밀번호가 맞지 않습니다.");
            return deleteDto;
        }
    }

    /**
     * 탈퇴 회원 보유 콘텐츠 수량 고지 api
     */
    @ApiOperation(value=" 탈퇴 회원 보유 콘텐츠 수량 고지하기 ", notes="NEED JWT IN HEADER: 탈퇴 회원 보유 콘텐츠 수량 고지하기")
    @GetMapping("/content-count")
    @ResponseBody
    public WithdrawDto countUserContentController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();

        Optional<User> findUser = userRepository.findByEmail(userEmail);
        Long userId = findUser.get().getId();
        return WithdrawDto.builder()
                .comment(commentRepository.countByUserId(userId))
                .moment(momentRepository.countByUserId(userId))
                .schedule(scheduleRepository.countByUserId(userId))
                .build();
    }
}
