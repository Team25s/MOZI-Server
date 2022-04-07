package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.LogInDto;
import mozi.mozispring.Domain.Dto.SignInDto;
import mozi.mozispring.Domain.Dto.WithdrawDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Jwt.JwtTokenProvider;
import mozi.mozispring.Repository.CommentRepository;
import mozi.mozispring.Repository.MomentRepository;
import mozi.mozispring.Repository.ScheduleRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import mozi.mozispring.Util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public LoginController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, MomentRepository momentRepository, CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.momentRepository = momentRepository;
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * 회원가입 api
     */
    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> joinController(@RequestBody SignInDto signInDto) {
        System.out.println("회원가입 요청입니다.");
        Optional<User> result = userRepository.findByEmail(signInDto.getEmail());
        if(result.isPresent()){
            return ResponseEntity.ok().body(new ErrorResponse("이미 존재하는 아이디입니다."));
        }else {
            return ResponseEntity.ok().body(new CommonResponse<>(userRepository.save(User.builder()
                    .email(signInDto.getEmail())
                    .password(passwordEncoder.encode(signInDto.getPassword()))
                    .name(signInDto.getName())
                    .build()).getId()));
        }
    }

    /**
     * 로그인 api
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> loginController(@RequestBody LogInDto logInDto) {
        System.out.println("로그인 요청입니다.");
        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());
        if(!findUser.isPresent()){
            return ResponseEntity.ok().body(new ErrorResponse("가입되지 않은 아이디입니다."));
        }
        User member = findUser.get();
        if (!passwordEncoder.matches(logInDto.getPassword(), member.getPassword())) {
            return ResponseEntity.ok().body(new ErrorResponse("잘못된 비밀번호입니다."));
        }
        return ResponseEntity.ok().body(new CommonResponse<>(jwtTokenProvider.createToken(member.getEmail())));
    }

    /**
     * 회원탈퇴 api
     */
    @PostMapping("/withdraw")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> withdrawController(@RequestBody LogInDto logInDto){
        System.out.println("회원탈퇴 요청입니다. ");
        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());
        if(!findUser.isPresent()){
            return ResponseEntity.ok().body(new ErrorResponse("탈퇴하려는 계정이 존재하지 않습니다."));
        }
        if(findUser.get().getPassword().equals(logInDto.getPassword())) {
            userRepository.deleteById(findUser.get().getId());   // 회원 디비에서 삭제
            return ResponseEntity.ok().body(new CommonResponse<>("성공적으로 탈퇴되었습니다"));
        }else{
            return ResponseEntity.ok().body(new ErrorResponse("비밀번호가 맞지 않습니다."));
        }
    }

    /**
     * 탈퇴 회원 콘텐츠 수량 고지 api
     */
    @GetMapping("/withdraw/content")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> countUserContentController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();

        Optional<User> findUser = userRepository.findByEmail(userEmail);
        Long userId = findUser.get().getId();
        return ResponseEntity.ok().body(new CommonResponse<>(WithdrawDto.builder()
                .comment(commentRepository.countByUserId(userId))
                .moment(momentRepository.countByUserId(userId))
                .schedule(scheduleRepository.countByUserId(userId))
                .build()));
    }
}
