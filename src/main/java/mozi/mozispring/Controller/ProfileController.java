package mozi.mozispring.Controller;

import com.google.cloud.storage.StorageException;
import mozi.mozispring.Domain.Dto.ProfileDto;
import mozi.mozispring.Domain.Dto.ProfileFixDto;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.SimplUserRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Service.FireBaseService;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> getUserProfileController(Long id){
        Optional<User> findUser = userRepository.findById(id);
        User user = findUser.get();
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileFileName(user.getProfileFilename());
        profileDto.setName(user.getName());
        profileDto.setMbti(user.getMbti());
        profileDto.setIntroduce(user.getIntroduce());
        profileDto.setStrTag(user.getStrTag());
        return ResponseEntity.ok().body(new CommonResponse(profileDto));
    }

    /**
     * 유저 프로필 수정
     */
    @PutMapping("/profile")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> updateProfileController(@RequestBody ProfileFixDto profileFixDto) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        User user = findUser.get();
        user.setName(profileFixDto.getName());
        user.setIntroduce(profileFixDto.getIntroduce());
        user.setStrTag(profileFixDto.getStrTag()); // 이부분 제대로 들어가는지 테스트 필요함.
        user.setMbti(profileFixDto.getMbti());

        // 회원 요약 정보도 함께 수정
        SimplUser simplUser = simplUserRepository.findById(user.getId()).get();
        simplUser.setName(profileFixDto.getName());
        simplUser.setMbti(profileFixDto.getMbti());

        if(!profileFixDto.getMultipartFile().isEmpty()){
            try {
                fireBaseService.deleteFiles(user.getProfileFilename());
            }catch(StorageException e){
                System.out.println(e.getMessage());
            }
            String nameFile =  UUID.randomUUID().toString() + ".jpg";
            String mediaLink = fireBaseService.uploadFiles(profileFixDto.getMultipartFile(), nameFile); // 이미지의 주소를 반환함.

            user.setProfileFilename(nameFile);
            simplUser.setProfileFilename(nameFile);
        }
        User result = userRepository.save(user);
                      simplUserRepository.save(simplUser);
        return ResponseEntity.ok().body(new CommonResponse<>(result.getId()));
    }
}
