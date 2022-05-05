package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
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

import java.util.*;

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
    @ApiOperation(value="친구 추가하기", notes="NEED JWT IN HEADER: 친구 추가하기")
    @PostMapping("/friend")
    @ResponseBody
    public Long addFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        Friend user = friendRepository.save(Friend.builder()
                .userId(findUser.get().getId())
                .friendId(friendDto.getFriendId())
                .mbti(friendDto.getMbti())
                .knock(0)
                .build());
        Friend friend = friendRepository.save(Friend.builder()
                .userId(friendDto.getFriendId())
                .friendId(findUser.get().getId())
                .mbti(findUser.get().getMbti())
                .knock(0)
                .build());

        return friend.getId();
    }

    /**
     * 친구 삭제
     */
    @ApiOperation(value="친구 삭제하기", notes="NEED JWT IN HEADER: 친구 삭제하기")
    @DeleteMapping("/friend")
    @ResponseBody
    public void deleteFriendController(@RequestBody FriendDto friendDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        friendRepository.deleteByUserIdAndFriendId(findUser.get().getId(), friendDto.getFriendId());
        friendRepository.deleteByUserIdAndFriendId(friendDto.getFriendId(), findUser.get().getId());
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
        return friendRetDtos;
    }
}
