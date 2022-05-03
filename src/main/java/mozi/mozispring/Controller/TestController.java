package mozi.mozispring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    // swagger 3.0 링크
    //http://localhost:8080/swagger-ui/
}
