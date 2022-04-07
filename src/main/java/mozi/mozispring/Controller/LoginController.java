package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.LogInDto;
import mozi.mozispring.Domain.Dto.SignInDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Jwt.JwtTokenProvider;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    /**
     * 회원가입 api
     */
    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> join(@RequestBody SignInDto signInDto) {
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
    public ResponseEntity<? extends BasicResponse> login(@RequestBody LogInDto logInDto) {
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
    public boolean withdraw(@RequestBody LogInDto logInDto){
        System.out.println("회원탈퇴 요청입니다. ");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();

        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(findUser.isPresent()) {
            if(findUser.get().getPassword().equals(password)) {
                userRepository.deleteById(findUser.get().getId());
                return true;
            }
            return false;
        }else{
            return false;
        }
    }
}
