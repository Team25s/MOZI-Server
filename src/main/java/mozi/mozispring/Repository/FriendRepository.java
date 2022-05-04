package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Override
    Friend save(Friend friend);

    @Override
    void deleteById(Long friendId);

    void deleteByUserIdAndFriendId(Long userId, Long friendId);
    Friend findByUserIdAndFriendId(Long userId, Long friendId);
    List<Friend> findAllById(Long userId);
    Optional<Friend> findById(Long id);
    Friend findByUserId(Long opponentId);
}
