package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentRepository extends JpaRepository<Moment, Long> {

    @Override
    Moment save(Moment moment);

    Long countByUserId(Long userId);
}
