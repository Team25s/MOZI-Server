package mozi.mozispring.Repository;

import mozi.mozispring.Domain.MomentPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentPhotoRepository extends JpaRepository<MomentPhoto, Long> {

    MomentPhoto save(MomentPhoto momentPhoto);
    List<MomentPhoto> findAllByMomentId(Long id);
    List<MomentPhoto> findAllById(Long id);
    void deleteById(Long aLong);
}
