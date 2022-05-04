package mozi.mozispring.Controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @PostMapping("/knock")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> knockController(@RequestBody KnockDto knockDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        // 나 - userId
        // 친구 - friendId
        Friend friend = friendRepository.findByUserIdAndFriendId(knockDto.getOpponentId(), findUser.get().getId());
        friend.setKnock(friend.getKnock() + 1);
        return ResponseEntity.ok().body(new CommonResponse<>(friendRepository.save(friend)));
    }

    /**
     * 노크 로그 반환
     */
    @GetMapping("/knock")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> getKnockController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        List<Friend> friendList = friendRepository.findAllByUserId(findUser.get().getId());
        return ResponseEntity.ok().body(new CommonResponse<>(friendList));
    }

    /**
     * 노크 삭제
     */
    @GetMapping("/knock")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> deleteKnockController(@RequestBody KnockDelDto knockDelDto){
        Friend friend = friendRepository.findById(knockDelDto.getKnockId()).get();
        friend.setKnock(0);
        friendRepository.save(friend); // 초기화
        return ResponseEntity.ok().body(new CommonResponse<>("노크가 초기화되었습니다."));
    }
}
