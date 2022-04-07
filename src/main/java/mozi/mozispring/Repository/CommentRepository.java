package mozi.mozispring.Repository;

import mozi.mozispring.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    @Override
    Comment save(Comment comment);

    @Override
    void deleteById(Long aLong);

    @Override
    Optional<Comment> findById(Long commentId);
}
