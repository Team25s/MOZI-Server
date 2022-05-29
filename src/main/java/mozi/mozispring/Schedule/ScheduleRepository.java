package mozi.mozispring.Schedule;

import mozi.mozispring.Domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Long countByUserId(Long userId);

    @Override
    Schedule save(Schedule entity);

    List<Schedule> findAllById(Long id);
    Optional<Schedule> findByTitle(String title);
    void deleteById(Long id);
    void deleteAllById(Long id);
    void deleteAllByUserId(Long userId);
}
