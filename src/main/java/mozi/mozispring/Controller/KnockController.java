package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.KnockDelDto;
import mozi.mozispring.Domain.Dto.KnockDto;
import mozi.mozispring.Domain.Friend;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.FriendRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class KnockController {

    private FriendRepository friendRepository;
    private UserRepository userRepository;

    @Autowired
    public KnockController(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    /**
     * 상대방에게 노크
     * 주의사항: 서로 친구 추가가 되어있어야 노크 가능
     */
    @ApiOperation(value="상대방에게 노크하기 ", notes="NEED JWT IN HEADER: 상대방에게 노크하기, 주의사항: 서로 친구 추가가 되어있어야 노크 가능")
    @PostMapping("/knock")
    @ResponseBody
    public Long knockController(@RequestBody KnockDto knockDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        // 나 - userId
        // 친구 - friendId
        Friend friend = friendRepository.findByUserIdAndFriendId(knockDto.getOpponentId(), findUser.get().getId());
        friend.setKnock(friend.getKnock() + 1);
        return friendRepository.save(friend).getId();
    }

    /**
     * 노크 로그 반환
     */
    @ApiOperation(value="노크 로그 반환 ", notes="NEED JWT IN HEADER: 노크 로그 반환")
    @GetMapping("/knock")
    @ResponseBody
    public List<Friend> getKnockController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        List<Friend> friendList = friendRepository.findAllByUserId(findUser.get().getId());
        return friendList;
    }

    /**
     * 노크 삭제
     */
    @ApiOperation(value="노크 삭제하기 ", notes="노크 삭제하기")
    @DeleteMapping("/knock")
    @ResponseBody
    public Long deleteKnockController(@RequestBody KnockDelDto knockDelDto){
        Friend friend = friendRepository.findById(knockDelDto.getKnockId()).get();
        friend.setKnock(0); // 초기화
        return friendRepository.save(friend).getId();
    }
}
