package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.MomentDto;
import mozi.mozispring.Domain.Dto.MomentRetDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.MomentPhotoRepository;
import mozi.mozispring.Repository.MomentRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Service.FireBaseService;
import mozi.mozispring.Service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MomentController {

    private FireBaseService fireBaseService;
    private MomentRepository momentRepository;
    private MomentPhotoRepository momentPhotoRepository;
    private UserRepository userRepository;
    private MomentService momentService;

    @Autowired
    public MomentController(FireBaseService fireBaseService, MomentRepository momentRepository, MomentPhotoRepository momentPhotoRepository, UserRepository userRepository, MomentService momentService) {
        this.fireBaseService = fireBaseService;
        this.momentRepository = momentRepository;
        this.momentPhotoRepository = momentPhotoRepository;
        this.userRepository = userRepository;
        this.momentService = momentService;
    }

    /**
     * 특정 유저의 모든 모먼트 모두 불러오기
     */
    @GetMapping("/moment-list/{id}")
    @ResponseBody
    public List<MomentRetDto> getAllMomentController(@PathVariable("id") Long id){ // PathVariable로 유저 id 전달
        List<Moment> momentList = momentRepository.findAllByUserId(id);
        List<MomentRetDto> momentRetDtoList = new ArrayList<>();
        for(Moment moment : momentList){
            momentRetDtoList.add(momentService.getMoment(moment.getId()));
        }
        return momentRetDtoList;
    }

    /**
     * 유저의 모먼트 한 건 불러오기
     */
    @ApiOperation(value="유저의 모먼트 한 건 불러오기", notes="유저의 모먼트 한 건 불러오기")
    @GetMapping("/moment/{id}")
    @ResponseBody
    public MomentRetDto getSingleMomentController(@PathVariable("id") Long momentId){ // PathVariable로 모먼트 id 전달
        return momentService.getMoment(momentId);
    }

    /**
     * 모먼트 기록하기
     */
    @ApiOperation(value="모먼트 기록하기", notes="모먼트 기록하기")
    @PostMapping("/moment")
    @ResponseBody
    public Long createMomentController(@RequestBody MomentDto momentDto){
        Moment moment = new Moment();
        moment.setTitle(moment.getTitle());
        moment.setContent(moment.getContent());
        moment.setDate(moment.getDate());
        moment.setUserId(moment.getUserId());
        moment.setHashTag(moment.getHashTag());
        Moment savedMoment = momentRepository.save(moment);

        try {
            for (MultipartFile multipartFile : momentDto.getMultipartFiles()) {
                String filename = UUID.randomUUID().toString() + ".jpg";
                String mediaLink = fireBaseService.uploadFiles(multipartFile, filename);

                MomentPhoto momentPhoto = new MomentPhoto();
                momentPhoto.setMomentId(savedMoment.getId());
                momentPhoto.setFileName(filename);
                momentPhotoRepository.save(momentPhoto);
            }
        }catch(Exception e){
            return -1L;
        }
        return savedMoment.getId();
    }

    /**
     * 모먼트 삭제하기
     */
    @ApiOperation(value="모먼트 삭제하기", notes="NEED JWT IN HEADER: 모먼트 삭제하기")
    @DeleteMapping("/moment/{id}")
    @ResponseBody
    public boolean deleteMomentController(@PathVariable("id") Long id){ // 모먼트 id 를 파라미터로 전달
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        Moment findMoment = momentRepository.findById(id).get();
        if(!(findMoment.getUserId() == findUser.get().getId())){ // 자신의 모먼트가 아닌 것을 삭제하려고 하는 경우
            return false;
        }
        List<MomentPhoto> momentPhotoList = momentPhotoRepository.findAllByMomentId(id);
        int count = 0;
        int len = momentPhotoList.size();
        momentRepository.deleteById(findMoment.getId());
        for(MomentPhoto momentPhoto: momentPhotoList){
            try {
                fireBaseService.deleteFiles(momentPhoto.getFileName()); // 파이어베이스에서 이미지 삭제
                momentPhotoRepository.deleteById(momentPhoto.getId());  // 디비에서 모먼트 사진 정보 삭제
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            count++;
        }
        if(count == len){
            return true;
        }
        return false;
    }

    /**
     * 해시태그로 검색하기
     */
    @ApiOperation(value="해시태그로 검색하기", notes="해시태그로 검색하기")
    @GetMapping("/moment/tag")
    @ResponseBody
    public void getMomentByHashTag(){

    }
}
