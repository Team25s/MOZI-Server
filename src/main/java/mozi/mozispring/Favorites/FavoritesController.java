package mozi.mozispring.Favorites;

import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.FavoritesDto;
import mozi.mozispring.Domain.Favorites;
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
public class FavoritesController {
    private final FavoritesRepository favoritesRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoritesController(FavoritesRepository favoritesRepository, UserRepository userRepository) {
        this.favoritesRepository = favoritesRepository;
        this.userRepository = userRepository;
    }

    /**
     * 내 츨겨찾기 목록 반환
     */
    @GetMapping("/favorites")
    @ResponseBody
    public List<Favorites> getFavoritesController(){
        System.out.println("내 츨겨찾기 목록 반환: getFavoritesController");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return favoritesRepository.findAllByUserId(findUser.get().getId());
    }

    /**
        즐겨찾기 등록
     */
    @PostMapping("/favorites")
    @ResponseBody
    public Favorites registerFavoritesController(@RequestBody FavoritesDto favoritesDto){
        System.out.println("즐겨찾기 등록: registerFavoritesController");

        Favorites favorites = new Favorites();
        favorites.setOpponentId(favoritesDto.getOpponentId());
        favorites.setUserId(favoritesDto.getUserId());
        return favoritesRepository.save(favorites);
    }

    /**
         즐겨찾기 삭제
     */
    @DeleteMapping("/favorites/{opponent-id}")
    @ResponseBody
    public DeleteDto deleteFavoritesController(@PathVariable("opponent-id") Long opponentId){
        System.out.println("즐겨찾기 삭제: deleteFavoritesController");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        DeleteDto deleteDto = new DeleteDto();
        try {
            favoritesRepository.deleteByUserIdAndOpponentId(findUser.get().getId(), opponentId);
        }catch(Exception e){
            deleteDto.setDeleted(false);
            deleteDto.setMessage("즐겨찾기에서 상대방을 삭제하지 못했습니다. ");
            return deleteDto;
        }
        deleteDto.setDeleted(true);
        deleteDto.setMessage("즐겨찾기에서 상대방을 삭제했습니다.");
        return deleteDto;
    }
}
