package mozi.mozispring.Game;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.AnswerDto;
import mozi.mozispring.Domain.Dto.QuestionDto;
import mozi.mozispring.Domain.GameLog;
import mozi.mozispring.Domain.GameQA;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    private GameRepository gameRepository;
    private UserRepository userRepository;
    private GameLogRepository gameLogRepository;
    private GameService gameService;

    @Autowired
    public GameController(GameRepository gameRepository, UserRepository userRepository, GameLogRepository gameLogRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.gameLogRepository = gameLogRepository;
        this.gameService = gameService;
    }

    /**
     * 모든 밸런스 게임 불러오기 
     */
    @ApiOperation(value="모든 밸런스 게임 불러오기 ", notes="모든 밸런스 게임 불러오기 ")
    @GetMapping("/game-list")
    @ResponseBody
    public List<GameQA> getGameListController(){
        System.out.println("모든 밸런스 게임 불러오기 : getGameListController");

        return gameRepository.findAll();
    }

    /**
     * 새로운 밸런스 게임 등록
     */
    @ApiOperation(value="새로운 밸런스 게임 등록", notes="새로운 밸런스 게임 등록")
    @PostMapping("/game")
    @ResponseBody
    public GameQA makeGameController(@RequestBody QuestionDto questionDto){
        System.out.println("새로운 밸런스 게임 등록 : makeGameController");

        return gameService.makeGame(questionDto); // 새로운 밸런스 게임 등록
    }

    /**
     * 밸런스 게임 플레이
     */
    @ApiOperation(value="밸런스 게임 플레이", notes="NEED JWT IN HEADER: 밸런스 게임 플레이")
    @PostMapping("/game-play")
    @ResponseBody
    public GameLog answerGameController(@RequestBody AnswerDto answerDto){
        System.out.println("밸런스 게임 플레이 : answerGameController");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return gameService.answerGame(answerDto, findUser);
    }

    /**
     * 밸런스 게임 통계 보기
     */
    @ApiOperation(value="밸런스 게임 통계 보기", notes="밸런스 게임 통계 보기")
    @GetMapping("/game-chart/{id}")
    @ResponseBody
    public GameLog getGameChartController(@PathVariable("id") Long id){
        System.out.println("밸런스 게임 통계 보기 : getGameChartController");

        GameLog gameLog = gameLogRepository.findByQuestionId(id);
        return gameLog;
    }
}
