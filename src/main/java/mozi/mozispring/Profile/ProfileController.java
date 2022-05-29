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

    @Autowired
    public ProfileController(UserRepository userRepository, FireBaseService fireBaseService, SimplUserRepository simplUserRepository) {
        this.userRepository = userRepository;
        this.fireBaseService = fireBaseService;
        this.simplUserRepository = simplUserRepository;
    }

    /**
     *  유저 프로필 불러오기
     */
    @ApiOperation(value="유저 프로필 불러오기", notes="유저 프로필 불러오기")
    @GetMapping("/profile")
    @ResponseBody
    public ProfileDto getUserProfileController(Long id){
        Optional<User> findUser = userRepository.findById(id);
        User user = findUser.get();
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileFileName(user.getProfileFilename()); // 프로필 이미지 이름
        profileDto.setProfileFileURL(user.getProfileFileURL());   // 프로필 이미지 링크
        profileDto.setName(user.getName());
        profileDto.setMbti(user.getMbti());
        profileDto.setIntroduce(user.getIntroduce());
        profileDto.setStrTag(user.getStrTag());
        return profileDto;
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

        User user = findUser.get();
        user.setName(profileFixDto.getName());
        user.setIntroduce(profileFixDto.getIntroduce());
        user.setStrTag(profileFixDto.getStrTag());
        user.setMbti(profileFixDto.getMbti());

        // 회원 요약 정보도 함께 수정
        SimplUser simplUser = simplUserRepository.findById(user.getId()).get();
        simplUser.setName(profileFixDto.getName());
        simplUser.setMbti(profileFixDto.getMbti());

        if(!profileFixDto.getMultipartFile().isEmpty()){
            try {
                fireBaseService.deleteFiles(user.getProfileFilename()); // 기존에 존재하던 이미지는 삭제함.
            }catch(StorageException e){
                System.out.println(e.getMessage());
            }
            String nameFile= null;
            String mediaLink= null;
            try {
                nameFile = UUID.randomUUID().toString() + ".jpg";
                mediaLink = fireBaseService.uploadFiles(profileFixDto.getMultipartFile(), nameFile);   // 이미지의 주소를 반환함.
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
            user.setProfileFilename(nameFile); // 새로운 이미지 파일명 설정
            user.setProfileFileURL(mediaLink); // 새로운 이미지 링크 설정
            simplUser.setProfileFilename(nameFile);
            simplUser.setProfileFileURL(mediaLink);
        }
        User savedUser = userRepository.save(user);                   // 유저 정보 저장
        SimplUser savedSimplUser = simplUserRepository.save(simplUser);  // 유저 요약 정보 저장

        return savedSimplUser; // 사용자 요약정보를 반환
    }
}
