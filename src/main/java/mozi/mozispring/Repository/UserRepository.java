package mozi.mozispring.Repository;

import mozi.mozispring.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User entity);
    void deleteById(Long userId);
    Optional<User> findByEmail(String email);
}
