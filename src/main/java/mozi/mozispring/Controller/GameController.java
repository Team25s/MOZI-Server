package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.AnswerDto;
import mozi.mozispring.Domain.Dto.QuestionDto;
import mozi.mozispring.Domain.GameQA;
import mozi.mozispring.Repository.GameRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * 모든 밸런스 게임 불러오기 
     */

    /**
     * 새로운 밸런스 게임 등록
     */
    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> makeGameController(@RequestBody QuestionDto questionDto){
        GameQA newGameQA = new GameQA();
        newGameQA.setQuestion(questionDto.getQuestion());
        newGameQA.setPositive_answer(0);
        newGameQA.setNegative_answer(0);
        return ResponseEntity.ok().body(new CommonResponse<>(gameRepository.save(newGameQA)));
    }

    /**
     * 밸런스 게임에 응답하기
     */
    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> answerGameController(@RequestBody AnswerDto answerDto){
        GameQA gameQA = gameRepository.findById(answerDto.getQuestionId()).get();
        if (answerDto.isPositive_answer()){
            gameQA.setNegative_answer(gameQA.getPositive_answer() + 1);
        }else{
            gameQA.setPositive_answer(gameQA.getNegative_answer() + 1);
        }
        return ResponseEntity.ok().body(new CommonResponse<>(gameRepository.save(gameQA)));
    }

    /**
     * 밸런스 게임 통계 보기
     */

}
