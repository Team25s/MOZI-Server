package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.QuestionDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    /**
     * 새로운 문항 등록
     */
    @PostMapping("/game")
    @ResponseBody
    public void makeGameController(@RequestBody QuestionDto questionDto){

    }

    /**
     * 문항에 응답하기
     */

    /**
     * 응답 통계 보기
     */

}
