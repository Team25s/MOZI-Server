package mozi.mozispring.Comment;

import mozi.mozispring.Domain.Comment;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    @Override
    Comment save(Comment comment);

    @Override
    void deleteById(Long aLong);

    @Override
    Optional<Comment> findById(Long commentId);

    Long countByUserId(Long userId);

    List<Comment> findAllById(Long userId);

    void deleteAllByUserId(Long userId);
}

