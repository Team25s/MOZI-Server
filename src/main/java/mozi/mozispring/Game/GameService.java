package mozi.mozispring.Game;

import mozi.mozispring.Domain.Dto.QuestionDto;
import mozi.mozispring.Domain.GameLog;
import mozi.mozispring.Domain.GameQA;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameLogRepository gameLogRepository;

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
}
