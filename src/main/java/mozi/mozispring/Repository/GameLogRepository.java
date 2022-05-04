package mozi.mozispring.Repository;

import mozi.mozispring.Domain.GameLog;
import mozi.mozispring.Domain.GameQA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLogRepository extends JpaRepository<GameLog, Long> {
    GameLog save(GameLog gameLog);
    GameLog findByQuestionId(Long questionId);
}
