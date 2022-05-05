package mozi.mozispring.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    /**
     * Swagger3 api 스펙 명시 링크 예시:
     * http://localhost:8080/swagger-ui/
     */
}
