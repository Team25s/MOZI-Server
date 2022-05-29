package mozi.mozispring.Game;

import mozi.mozispring.Domain.Dto.AnswerDto;
import mozi.mozispring.Domain.Dto.QuestionDto;
import mozi.mozispring.Domain.GameLog;
import mozi.mozispring.Domain.GameQA;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private GameRepository gameRepository;
    private GameLogRepository gameLogRepository;

    public GameService(GameRepository gameRepository, GameLogRepository gameLogRepository) {
        this.gameRepository = gameRepository;
        this.gameLogRepository = gameLogRepository;
    }

    /**
     *  새로운 밸런스 게임 등록
     */
    public GameQA makeGame(QuestionDto questionDto) {
        GameQA newGameQA = new GameQA();
        newGameQA.setQuestion(questionDto.getQuestion());
        newGameQA.setPositive_answer(0);
        newGameQA.setNegative_answer(0);
        GameQA gameQA = gameRepository.save(newGameQA);

        GameLog gameLog = new GameLog();      // 로그도 함께 생성
        gameLog.setQuestionId(gameQA.getId());
        gameLogRepository.save(gameLog);
        return gameQA;
    }

    public GameLog answerGame(AnswerDto answerDto, Optional<User> findUser) {

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
                    gameLog.setEnfj_positive(gameLog.getEnfj_positive() + 1);
                }else{
                    gameLog.setEnfj_negative(gameLog.getEnfj_negative() + 1);
                }
                break;
            case "enfp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEnfp_positive(gameLog.getEnfp_positive() + 1);
                }else{
                    gameLog.setEnfp_negative(gameLog.getEnfp_negative() + 1);
                }
                break;
            case "esfj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEsfj_positive(gameLog.getEsfj_positive() + 1);
                }else{
                    gameLog.setEsfj_negative(gameLog.getEsfj_negative() + 1);
                }
                break;
            case "entj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEntj_positive(gameLog.getEntj_positive() + 1);
                }else{
                    gameLog.setEntj_negative(gameLog.getEntj_negative() + 1);
                }
                break;
            case "estj":
                if(answerDto.isPositive_answer()){
                    gameLog.setEstj_positive(gameLog.getEstj_positive() + 1);
                }else{
                    gameLog.setEstj_negative(gameLog.getEstj_negative() + 1);
                }
                break;
            case "estp":
                if(answerDto.isPositive_answer()){
                    gameLog.setEstp_positive(gameLog.getEstp_positive() + 1);
                }else{
                    gameLog.setEstp_negative(gameLog.getEstp_negative() + 1);
                }
                break;
            case "infp":
                if(answerDto.isPositive_answer()){
                    gameLog.setInfp_positive(gameLog.getInfp_positive() + 1);
                }else{
                    gameLog.setInfp_negative(gameLog.getInfp_negative() + 1);
                }
                break;
            case "infj":
                if(answerDto.isPositive_answer()){
                    gameLog.setInfj_positive(gameLog.getInfj_positive() + 1);
                }else{
                    gameLog.setInfj_negative(gameLog.getInfj_negative() + 1);
                }
                break;
            case "isfp":
                if(answerDto.isPositive_answer()){
                    gameLog.setIsfp_positive(gameLog.getIsfp_positive() + 1);
                }else{
                    gameLog.setIsfp_negative(gameLog.getIsfp_negative() + 1);
                }
                break;
            case "isfj":
                if(answerDto.isPositive_answer()){
                    gameLog.setIsfj_positive(gameLog.getIsfj_positive() + 1);
                }else{
                    gameLog.setIsfj_negative(gameLog.getIsfj_negative() + 1);
                }
                break;
            case "intj":
                if(answerDto.isPositive_answer()){
                    gameLog.setIntj_positive(gameLog.getIntj_positive() + 1);
                }else{
                    gameLog.setIntj_negative(gameLog.getIntj_negative() + 1);
                }
                break;
            case "istp":
                if(answerDto.isPositive_answer()){
                    gameLog.setIstp_positive(gameLog.getIstp_positive() + 1);
                }else{
                    gameLog.setIstp_negative(gameLog.getIstp_negative() + 1);
                }
                break;
            case "istj":
                if(answerDto.isPositive_answer()){
                    gameLog.setIstj_positive(gameLog.getIstj_positive() + 1);
                }else{
                    gameLog.setIstj_negative(gameLog.getIstj_negative() + 1);
                }
                break;
            case "intp":
                if(answerDto.isPositive_answer()){
                    gameLog.setIntp_positive(gameLog.getIntp_positive() + 1);
                }else{
                    gameLog.setIntp_negative(gameLog.getIntp_negative() + 1);
                }
                break;
            case "xxxx":
                if(answerDto.isPositive_answer()){
                    gameLog.setXxxx_positive(gameLog.getXxxx_positive() + 1);
                }else{
                    gameLog.setXxxx_negative(gameLog.getXxxx_negative() + 1);
                }
                break;
            default:
                return gameLog;
        }
        return gameLogRepository.save(gameLog);
    }
}
