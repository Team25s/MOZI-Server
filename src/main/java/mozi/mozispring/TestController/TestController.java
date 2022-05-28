package mozi.mozispring.TestController;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
