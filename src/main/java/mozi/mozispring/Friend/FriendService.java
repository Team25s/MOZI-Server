package mozi.mozispring.Friend;

import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.FriendDto;
import mozi.mozispring.Domain.Dto.FriendRetDto;
import mozi.mozispring.Domain.Friend;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Favorites.FavoritesRepository;
import mozi.mozispring.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final FavoritesRepository favoritesRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, FavoritesRepository favoritesRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.favoritesRepository = favoritesRepository;
        this.userRepository = userRepository;
    }

    /**
     * 친구 추가
     */
    public Friend addFriend(FriendDto friendDto, Optional<User> findUser){
        // 클라이언트 요구사항: 중복체크
        Friend friend1 = friendRepository.findByUserIdAndFriendId(friendDto.getFriendId(), findUser.get().getId());
        Friend friend2 = friendRepository.findByUserIdAndFriendId(findUser.get().getId(), friendDto.getFriendId());
        if(friend1 == null || friend2 == null){
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
        }else{
            Friend friend = new Friend();
            friend.setId(-1L);
            return friend;
        }
    }

    /**
     *  친구 삭제
     */
    public DeleteDto deleteFriend(FriendDto friendDto, Optional<User> findUser) {
        DeleteDto deleteDto = new DeleteDto();
        try {
            // 서로를 친구 목록에서 삭제해준다.
            try {
                friendRepository.deleteByUserIdAndFriendId(findUser.get().getId(), friendDto.getFriendId());
                friendRepository.deleteByUserIdAndFriendId(friendDto.getFriendId(), findUser.get().getId());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            // 즐겨찾기 목록에서도 서로를 삭제해준다.
            try {
                favoritesRepository.deleteByUserIdAndOpponentId(findUser.get().getId(), friendDto.getFriendId());
                favoritesRepository.deleteByUserIdAndOpponentId(friendDto.getFriendId(), findUser.get().getId());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(Exception e){
            deleteDto.setDeleted(false);
            deleteDto.setMessage("친구 삭제를 할 수 없습니다.");
            return deleteDto;
        }
        deleteDto.setDeleted(true);
        deleteDto.setMessage("친구 삭제를 완료했습니다.");
        return deleteDto;
    }

    /**
     * 내 친구 목록 불러오기
     */
    public List<FriendRetDto> getFriendList(Optional<User> findUser) {
        List<Friend> friends = friendRepository.findAllByUserId(findUser.get().getId());
        List<FriendRetDto> friendRetDtos = new ArrayList<>();

        for(Friend friend : friends){
            User findFriend = userRepository.findById(friend.getFriendId()).get();
            FriendRetDto friendRetDto = new FriendRetDto();

            friendRetDto.setFriendId(friend.getFriendId());
            friendRetDto.setMbti(friend.getMbti());
            friendRetDto.setName(findFriend.getName());
            friendRetDto.setFilename(findFriend.getProfileFilename()); // 프로필 이미지 이름
            friendRetDto.setFileURL(findFriend.getProfileFileURL());  // 프로필 이미지 링크
            friendRetDtos.add(friendRetDto);
        }
        return friendRetDtos;
    }
}
