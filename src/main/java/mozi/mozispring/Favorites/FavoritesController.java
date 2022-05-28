package mozi.mozispring.Favorites;

import mozi.mozispring.Domain.Dto.DeleteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FavoritesController {
    private final FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesController(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    /**
     * 내 츨겨찾기 목록 반환
     */
    @GetMapping("/favorites")
    @ResponseBody
    public void getFavoritesController(){

    }

    /**
        즐겨찾기 등록
     */
    @PostMapping("/favorites")
    @ResponseBody
    public void registerFavoritesController(){

    }

    /**
     즐겨찾기 수정
     */
    @PutMapping("/favorites")
    @ResponseBody
    public void updateFavoritesController(){

    }

    /**
     즐겨찾기 삭제
     */
    @DeleteMapping("/favorites")
    @ResponseBody
    public DeleteDto deleteFavoritesController(){

    }
}
