package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.AnswerDto;
import mozi.mozispring.Domain.Dto.QuestionDto;
import mozi.mozispring.Domain.GameLog;
import mozi.mozispring.Domain.GameQA;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.GameLogRepository;
import mozi.mozispring.Repository.GameRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class GameController {

    private GameRepository gameRepository;
    private UserRepository userRepository;
    private GameLogRepository gameLogRepository;

    @Autowired
    public GameController(GameRepository gameRepository, UserRepository userRepository, GameLogRepository gameLogRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.gameLogRepository = gameLogRepository;
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
        GameQA gameQA = gameRepository.save(newGameQA);

        GameLog gameLog = new GameLog(); // 로그도 함께 생성
        gameLog.setQuestionId(gameQA.getId());
        gameLogRepository.save(gameLog);
        return ResponseEntity.ok().body(new CommonResponse<>(gameQA));
    }

    /**
     * 밸런스 게임에 응답하기
     */
    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> answerGameController(@RequestBody AnswerDto answerDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        GameQA gameQA = gameRepository.findById(answerDto.getQuestionId()).get();
        GameLog gameLog = gameLogRepository.findByQuestionId(gameQA.getId());
        String mbti = findUser.get().getMbti();

        switch(mbti){
            case "esfp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "entp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEntp_positive(gameLog.getEntp_positive() + 1);
                }else{
                    gameLog.setEntp_negative(gameLog.getEntp_negative() + 1);
                }
                break;
            case "enfj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "enfp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "esfj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "entj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "estj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "estp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "infp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "infj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "isfp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "isfj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "intj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "istp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "istj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "intp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            case "xxxx":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfp_positive(gameLog.getEsfp_positive() + 1);
                }else{
                    gameLog.setEsfp_negative(gameLog.getEsfp_negative() + 1);
                }
                break;
            default:
                return ResponseEntity.ok().body(new CommonResponse<>(gameQA));
        }
        return ResponseEntity.ok().body(new CommonResponse<>(gameLogRepository.save(gameLog)));
    }

    /**
     * 밸런스 게임 통계 보기
     */

}
