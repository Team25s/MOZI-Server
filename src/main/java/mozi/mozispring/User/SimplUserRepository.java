package mozi.mozispring.User;

import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface SimplUserRepository  extends JpaRepository<SimplUser, Long> {

    SimplUser save(SimplUser simplUser);
    Optional<SimplUser> findById(Long id);
    void deleteById(Long id);

    void deleteByEmail(String email);
}
