package mozi.mozispring.Comment;

import mozi.mozispring.Domain.Comment;
import mozi.mozispring.Domain.Dto.DelComment;
import mozi.mozispring.Domain.Dto.DeleteDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 댓글 삭제
     */
    public DeleteDto deleteComment(DelComment delComment, Long findUserId){
        Optional<Comment> findComment = commentRepository.findById(delComment.getCommentId());
        DeleteDto deleteDto = new DeleteDto();
        if(findUserId == findComment.get().getUserId()){
            commentRepository.deleteById(delComment.getCommentId());
            deleteDto.setDeleted(true);
            deleteDto.setMessage("정상적으로 삭제되었습니다.");
            return deleteDto;
        }else{
            deleteDto.setDeleted(false);
            deleteDto.setMessage("자신의 댓글만 삭제할 수 있습니다.");
            return deleteDto;
        }
    }
}
