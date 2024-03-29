package mozi.mozispring.Game;

import mozi.mozispring.Domain.GameQA;
import mozi.mozispring.Domain.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository  extends JpaRepository<GameQA, Long> {
    GameQA save(GameQA gameQA);
    Optional<GameQA> findById(Long id);
    List<GameQA> findAll();
    GameQA findByQuestion(String question);
}
