package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.ProfileDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Service.FireBaseService;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 홈 프로필 편집 api
     */
    @PutMapping("/profile")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> updateProfileController(@RequestBody ProfileDto profileDto) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();

        Optional<User> findUser = userRepository.findByEmail(userEmail);
        User user = findUser.get();
        user.setName(profileDto.getName());
        user.setIntroduce(profileDto.getIntroduce());
        user.setHashTag(profileDto.getTagList().toString()); // 이부분 제대로 들어가는지 테스트 필요함.
        user.setMbti(profileDto.getMbti());
        if(!profileDto.getMultipartFile().isEmpty()){
            fireBaseService.deleteFiles(user.getProfileFilename());
            String nameFile =  UUID.randomUUID().toString() + ".jpg";
            fireBaseService.uploadFiles(profileDto.getMultipartFile(), nameFile);
            user.setProfileFilename(nameFile);
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(new CommonResponse<>(result.getId()));
    }
}
