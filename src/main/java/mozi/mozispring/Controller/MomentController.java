package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.MomentDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Repository.MomentPhotoRepository;
import mozi.mozispring.Repository.MomentRepository;
import mozi.mozispring.Service.FireBaseService;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import mozi.mozispring.Util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class MomentController {

    private FireBaseService fireBaseService;
    private MomentRepository momentRepository;
    private MomentPhotoRepository momentPhotoRepository;

    @Autowired
    public MomentController(FireBaseService fireBaseService, MomentRepository momentRepository, MomentPhotoRepository momentPhotoRepository) {
        this.fireBaseService = fireBaseService;
        this.momentRepository = momentRepository;
        this.momentPhotoRepository = momentPhotoRepository;
    }

    /**
     * 유저의 모먼트 불러오기
     */
    @ApiOperation(value="유저의 모먼트 불러오기", notes="유저의 모먼트 불러오기")
    @GetMapping("/moment")
    @ResponseBody
    public void getAllMomentController(){

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
            //return ResponseEntity.ok().body(new ErrorResponse("모먼트를 저장할 수 없습니다."));
            return -1L;
        }
        //return ResponseEntity.ok().body(new CommonResponse<>("모먼트를 정상적으로 기록하였습니다."));
        return savedMoment.getId();
    }

    /**
     * 모먼트 삭제하기
     */
    @ApiOperation(value="모먼트 삭제하기", notes="모먼트 삭제하기")
    @DeleteMapping("/moment")
    @ResponseBody
    public void deleteMomentController(){

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
