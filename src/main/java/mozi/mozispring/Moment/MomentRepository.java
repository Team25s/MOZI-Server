package mozi.mozispring.Moment;

import mozi.mozispring.Domain.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MomentRepository extends JpaRepository<Moment, Long> {

    Moment save(Moment moment);

    Optional<Moment> findById(Long id);

    Long countByUserId(Long userId);

    void deleteById(Long aLong);

    List<Moment> findAllByUserId(Long userId);

    List<Moment> findByHashTagContaining(String tag);

    // Like 구문은 와일드 카드 적용해야함. '%tag%'
    List<Moment> findByHashTagLike(String tag);

    void deleteAllByUserId(Long userId);
}
