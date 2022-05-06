package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MomentRepository extends JpaRepository<Moment, Long> {

    Moment save(Moment moment);
    Optional<Moment> findById(Long id);
    Long countByUserId(Long userId);
    void deleteById(Long aLong);
}
