package mozi.mozispring.Profile;

import com.google.cloud.storage.StorageException;
import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.ProfileDto;
import mozi.mozispring.Domain.Dto.ProfileFixDto;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.SimplUserRepository;
import mozi.mozispring.User.UserRepository;
import mozi.mozispring.Firebase.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final FireBaseService fireBaseService;
    private final SimplUserRepository simplUserRepository;
    private final ProfileService profileService;


    @Autowired
    public ProfileController(UserRepository userRepository, FireBaseService fireBaseService, SimplUserRepository simplUserRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.fireBaseService = fireBaseService;
        this.simplUserRepository = simplUserRepository;
        this.profileService = profileService;
    }

    /**
     *  유저 프로필 불러오기
     */
    @ApiOperation(value="유저 프로필 불러오기", notes="유저 프로필 불러오기")
    @GetMapping("/profile/{id}")
    @ResponseBody
    public ProfileDto getUserProfileController(@PathVariable("id") Long id){
        return profileService.getUserProfile(id); // 유저 프로필 불러오기
    }

    /**
     * 유저 프로필 수정
     */
    @ApiOperation(value="유저 프로필 수정 ", notes="NEED JWT IN HEADER: 유저 프로필 수정")
    @PutMapping("/profile")
    @ResponseBody
    public SimplUser updateProfileController(@RequestBody ProfileFixDto profileFixDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return profileService.updateProfile(profileFixDto, findUser); // 프로필 수정하기
    }
}
