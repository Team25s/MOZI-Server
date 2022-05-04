package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentPhotoRepository extends JpaRepository<MomentPhoto, Long> {

    MomentPhoto save(MomentPhoto momentPhoto);


}
