package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Long countByUserId(Long userId);
}
