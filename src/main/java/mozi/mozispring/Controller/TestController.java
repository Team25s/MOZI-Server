package mozi.mozispring.Controller;

import mozi.mozispring.Domain.VO.TestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @PostMapping("/test1")
    public String test1(@RequestBody TestDto testDto){
        return testDto.getData();
    }

    // swagger 3.0 링크
    //http://localhost:8080/swagger-ui/
}
