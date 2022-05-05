package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.MomentDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import mozi.mozispring.Util.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping("/hello")
    @ApiOperation(value="테스트1 Controller", notes="테스트입니다.")
    public String Hello(){
        return "hello";
    }

    /**
     * Swagger3 api 스펙 명시 링크 예시:
     * http://localhost:8080/swagger-ui/
     *
     * Swagger2 api 스펙 명시 링크 예시:
     * http://localhost:8080/swagger-ui.html
     */


    /*
    @ApiOperation(value="모먼트 기록하기", notes="모먼트 기록하기")
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
     */
}
