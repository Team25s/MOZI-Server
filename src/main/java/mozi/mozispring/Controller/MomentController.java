package mozi.mozispring.Controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * 모먼트 기록하기
     */
    @PostMapping("/moment")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> createMomentController(@RequestBody MomentDto momentDto){
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
            return ResponseEntity.ok().body(new ErrorResponse("모먼트를 저장할 수 없습니다."));
        }
        return ResponseEntity.ok().body(new CommonResponse<>("모먼트를 정상적으로 기록하였습니다."));
    }

    /**
     * 모먼트 삭제하기
     */

    /**
     * 해시태그로 검색하기
     */
}
