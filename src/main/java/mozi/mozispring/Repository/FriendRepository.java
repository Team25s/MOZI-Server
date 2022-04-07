package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Override
    Friend save(Friend friend);

    @Override
    void deleteById(Long friendId);

    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
