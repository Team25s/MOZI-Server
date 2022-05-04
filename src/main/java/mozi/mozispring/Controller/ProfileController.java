package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.ProfileDto;
import mozi.mozispring.Domain.Dto.ProfileFixDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Service.FireBaseService;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
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

    public ProfileController(UserRepository userRepository, FireBaseService fireBaseService) {
        this.userRepository = userRepository;
        this.fireBaseService = fireBaseService;
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
        if(!profileFixDto.getMultipartFile().isEmpty()){
            fireBaseService.deleteFiles(user.getProfileFilename());
            String nameFile =  UUID.randomUUID().toString() + ".jpg";
            fireBaseService.uploadFiles(profileFixDto.getMultipartFile(), nameFile);
            user.setProfileFilename(nameFile);
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(new CommonResponse<>(result.getId()));
    }
}
