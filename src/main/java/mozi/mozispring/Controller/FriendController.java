package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.FriendDto;
import mozi.mozispring.Domain.Dto.FriendRetDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FriendController {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Autowired
    public FriendController(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    /**
     * 친구 추가
     */
    @PostMapping("/friend")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> addFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        friendRepository.save(Friend.builder()
                .userId(findUser.get().getId())
                .friendId(friendDto.getFriendId())
                .mbti(friendDto.getMbti())
                .knock(0)
                .build());
        friendRepository.save(Friend.builder()
                .userId(friendDto.getFriendId())
                .friendId(findUser.get().getId())
                .mbti(findUser.get().getMbti())
                .knock(0)
                .build());
        return ResponseEntity.ok().body(new CommonResponse<>("친구 추가에 성공하였습니다."));
    }

    /**
     * 친구 삭제
     */
    @DeleteMapping("/friend")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> deleteFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        friendRepository.deleteByUserIdAndFriendId(findUser.get().getId(), friendDto.getFriendId());
        friendRepository.deleteByUserIdAndFriendId(friendDto.getFriendId(), findUser.get().getId());
        return ResponseEntity.ok().body(new CommonResponse<>("성공적으로 삭제하였습니다."));
    }

    /**
     *  내 친구 목록
     */
    @GetMapping("/friend-list")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> getFriendListController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        List<Friend> friends = friendRepository.findAllById(findUser.get().getId());
        List<FriendRetDto> friendRetDtos = new ArrayList<>();

        for(Friend friend : friends){
            String filename = userRepository.findById(friend.getFriendId()).get().getProfileFilename();
            FriendRetDto friendRetDto = new FriendRetDto();

            friendRetDto.setFriendId(friend.getFriendId());
            friendRetDto.setMbti(friend.getMbti());
            friendRetDto.setFilename(filename);
            friendRetDtos.add(friendRetDto);
        }
        return ResponseEntity.ok().body(new CommonResponse<>(friendRetDtos));
    }
}
