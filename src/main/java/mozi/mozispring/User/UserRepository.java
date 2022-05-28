package mozi.mozispring.User;

import mozi.mozispring.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User entity);

    @Override
    void deleteById(Long userId);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);
}
