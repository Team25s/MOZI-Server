package mozi.mozispring.Friend;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.FriendDto;
import mozi.mozispring.Domain.Dto.FriendRetDto;
import mozi.mozispring.Domain.Friend;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Favorites.FavoritesRepository;
import mozi.mozispring.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class FriendController {

    private final UserRepository userRepository;
    private final FriendService friendService;


    public FriendController(UserRepository userRepository, FriendService friendService) {
        this.userRepository = userRepository;
        this.friendService = friendService;
    }

    /**
     * 친구 추가
     */
    @ApiOperation(value="친구 추가하기", notes="NEED JWT IN HEADER: 친구 추가하기")
    @PostMapping("/friend")
    @ResponseBody
    public Friend addFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return friendService.addFriend(friendDto, findUser); // 친구 추가
    }

    /**
     * 친구 삭제
     */
    @ApiOperation(value="친구 삭제하기", notes="NEED JWT IN HEADER: 친구 삭제하기")
    @DeleteMapping("/friend")
    @ResponseBody
    public DeleteDto deleteFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return friendService.deleteFriend(friendDto, findUser); // 친구 삭제
    }

    /**
     *  내 친구 목록
     */
    @ApiOperation(value="내 친구 목록 확인하기", notes="NEED JWT IN HEADER: 내 친구 목록 확인하기")
    @GetMapping("/friend-list")
    @ResponseBody
    public List<FriendRetDto> getFriendListController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return friendService.getFriendList(findUser); // 내 친구 목록 불러오기

    }
}
