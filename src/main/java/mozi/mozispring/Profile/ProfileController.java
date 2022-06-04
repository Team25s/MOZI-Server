package mozi.mozispring.Profile;

import com.google.cloud.storage.StorageException;
import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.ProfileDto;
import mozi.mozispring.Domain.Dto.ProfileFixDto;
import mozi.mozispring.Domain.Dto.TestProfileFixDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    /**
     * JWT를 사용해서 내 id알아내기
     */


    /**
     *  유저 프로필 불러오기
     */
    @ApiOperation(value="유저 프로필 불러오기", notes="유저 프로필 불러오기")
    @GetMapping("/profile/{id}")
    @ResponseBody
    public ProfileDto getUserProfileController(@PathVariable("id") Long id){
        System.out.println("유저 프로필 불러오기 : getUserProfileController");

        return profileService.getUserProfile(id); // 유저 프로필 불러오기
    }

    /**
     * 유저 프로필 수정 1
     */
    @ApiOperation(value="유저 프로필 수정 ", notes="NEED JWT IN HEADER: 유저 프로필 수정")
    @PutMapping("/profile1")
    @ResponseBody
    public SimplUser updateProfileController1(@RequestBody ProfileFixDto profileFixDto){
        System.out.println("유저 프로필 수정 1 : updateProfileController1");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        return profileService.updateProfile(profileFixDto, findUser); // 프로필 수정하기
    }

    /**
     * 유저 프로필 수정 2
     */
    @ApiOperation(value="유저 프로필 수정 ", notes="NEED JWT IN HEADER: 유저 프로필 수정")
    @PutMapping("/profile2")
    @ResponseBody
    public SimplUser updateProfileController2(@ModelAttribute ProfileFixDto profileFixDto){
        System.out.println("유저 프로필 수정 2 : updateProfileController2");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        return profileService.updateProfile(profileFixDto, findUser); // 프로필 수정하기
    }

    /**
     * 유저 프로필 수정 3
     */
    @ApiOperation(value="유저 프로필 수정 ", notes="NEED JWT IN HEADER: 유저 프로필 수정")
    @PutMapping("/profile3")
    @ResponseBody
    public SimplUser updateProfileController3(@RequestPart(value="key", required = false) TestProfileFixDto testProfileFixDto,
                                              @RequestPart(value="file", required = true) MultipartFile multipartFile){
        System.out.println("유저 프로필 수정 3 : updateProfileController3");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        return profileService.testUpdateProfile(testProfileFixDto, multipartFile, findUser); // 프로필 수정하기
    }

    /**
     * 테스트 api - 유저 프로필 수정 2
     */
    @ApiOperation(value="유저 프로필 수정 ", notes="NEED JWT IN HEADER: 유저 프로필 수정")
    @PostMapping("/profile4")
    @ResponseBody
    public SimplUser updateProfileController2(@RequestParam String name,
                                              @RequestParam String mbti,
                                              @RequestParam String introduce,
                                              @RequestParam String strTag,
                                              @RequestPart(value="file", required = false) MultipartFile multipartFile){
        System.out.println("테스트 api - 유저 프로필 수정 2: updateProfileController2");

        ProfileFixDto profileFixDto = new ProfileFixDto();
        profileFixDto.setName(name);
        profileFixDto.setIntroduce(introduce);
        profileFixDto.setMbti(mbti);
        profileFixDto.setStrTag(strTag);
        profileFixDto.setMultipartFile(multipartFile);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return profileService.updateProfile(profileFixDto, findUser); // 프로필 수정하기
    }

    /**
     * 내 프로필 이미지 저장
     */
    @PostMapping("/profile-image")
    @ResponseBody
    public void saveProfileImageFileController(@RequestPart(value="file", required = false) MultipartFile multipartFile){

    }

    /**
     * 모먼트 이미지 저장
     */
    @PostMapping("/moment-image")
    @ResponseBody
    public void saveMomentImageFileController(@RequestPart(value="file", required = false) List<MultipartFile> multipartFiles){

    }
}
