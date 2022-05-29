package mozi.mozispring.Friend;

import mozi.mozispring.Domain.Dto.FriendDto;
import mozi.mozispring.Domain.Friend;
import mozi.mozispring.Domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    /**
     * 친구 추가
     */
    public Friend addFriend(FriendDto friendDto, Optional<User> findUser){
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

        return friend;
    }
}
