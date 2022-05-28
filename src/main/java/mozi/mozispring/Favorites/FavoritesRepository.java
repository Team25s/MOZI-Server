package mozi.mozispring.Favorites;

import mozi.mozispring.Domain.Favorites;
import mozi.mozispring.Domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    @Override
    Favorites save(Favorites favorites);
    List<Favorites> findAllByUserId(Long userId);
    Favorites findByUserIdAndOpponentId(Long userId, Long opponentId);
    void deleteByUserIdAndOpponentId(Long userId, Long opponentId);
}

