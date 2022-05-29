package mozi.mozispring.Login;

import com.google.cloud.storage.StorageException;
import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Comment.CommentRepository;
import mozi.mozispring.Domain.Dto.*;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Favorites.FavoritesRepository;
import mozi.mozispring.Friend.FriendRepository;
import mozi.mozispring.Jwt.JwtTokenProvider;
import mozi.mozispring.Moment.MomentRepository;
import mozi.mozispring.Firebase.FireBaseService;
import mozi.mozispring.Profile.ProfileRepository;
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

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 회원가입 api
     */
    @ApiOperation(value="회원가입하기", notes="회원가입하기")
    @PostMapping("/join")
    @ResponseBody
    public JoinDto joinController(@RequestBody SignInDto signInDto) {
        return loginService.join(signInDto); // 회원 가입
    }

    /**
     * 로그인 api
     */
    @ApiOperation(value="로그인하기 ", notes="로그인하기")
    @PostMapping("/login")
    @ResponseBody
    public JwtRetDto loginController(@RequestBody LogInDto logInDto) {
        return loginService.login(logInDto);
    }

    /**
     * 회원탈퇴 api
     */
    @ApiOperation(value="회원탈퇴하기 ", notes="회원탈퇴하기")
    @PostMapping("/withdraw")
    @ResponseBody
    public DeleteDto withdrawController(@RequestBody LogInDto logInDto){
        return loginService.withdraw(logInDto);
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
        return loginService.countUserContent(userEmail);
    }
}
