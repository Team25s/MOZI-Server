package mozi.mozispring.Friend;

import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.FriendDto;
import mozi.mozispring.Domain.Friend;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Favorites.FavoritesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final FavoritesRepository favoritesRepository;

    public FriendService(FriendRepository friendRepository, FavoritesRepository favoritesRepository) {
        this.friendRepository = friendRepository;
        this.favoritesRepository = favoritesRepository;
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

    /**
     *  친구 삭제
     */
    public DeleteDto deleteFriend(FriendDto friendDto, Optional<User> findUser) {
        DeleteDto deleteDto = new DeleteDto();
        try {
            // 서로를 친구 목록에서 삭제해준다.
            friendRepository.deleteByUserIdAndFriendId(findUser.get().getId(), friendDto.getFriendId());
            friendRepository.deleteByUserIdAndFriendId(friendDto.getFriendId(), findUser.get().getId());
            // 즐겨찾기 목록에서도 서로를 삭제해준다.
            favoritesRepository.deleteByUserIdAndOpponentId(findUser.get().getId(), friendDto.getFriendId());
            favoritesRepository.deleteByUserIdAndOpponentId(friendDto.getFriendId(), findUser.get().getId());
        }catch(Exception e){
            deleteDto.setDeleted(false);
            deleteDto.setMessage("친구 삭제를 할 수 없습니다.");
            return deleteDto;
        }
        deleteDto.setDeleted(true);
        deleteDto.setMessage("친구 삭제를 완료했습니다.");
        return deleteDto;
    }
}
