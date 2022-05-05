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
}
