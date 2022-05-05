package mozi.mozispring.Controller;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Comment;
import mozi.mozispring.Domain.Dto.CommentDto;
import mozi.mozispring.Domain.Dto.DelComment;
import mozi.mozispring.Domain.Dto.UserIdDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.CommentRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import mozi.mozispring.Util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /**
     * 유저 모든 댓글 불러오기
     */
    @ApiOperation(value="유저 모든 댓글 불러오기", notes="유저 모든 댓글 불러오기")
    @GetMapping("/comment")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> getCommentController(@RequestBody UserIdDto userIdDto){
        List<Comment> commentList = commentRepository.findAllById(userIdDto.getId());
        return ResponseEntity.ok().body(new CommonResponse<>(commentList));
    }

    /**
     * 댓글 작성
     */
    @ApiOperation(value="댓글 작성하기", notes="댓글 작성하기")
    @PostMapping("/comment")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> commentController(@RequestBody CommentDto commentDto){
        Comment comment = commentRepository.save(Comment.builder()
                .userId(commentDto.getOpponentId())
                .content(commentDto.getContent())
                .build());
        return ResponseEntity.ok().body(new CommonResponse<>(comment.getId()));
    }

    /**
     * 댓글 삭제
     */
    @ApiOperation(value="댓글 삭제하기", notes="댓글 삭제하기")
    @DeleteMapping("/comment")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> deleteCommentController(@RequestBody DelComment delComment){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);

        Optional<Comment> findComment = commentRepository.findById(delComment.getCommentId());

        if(findUser.get().getId().equals(findComment.get().getUserId())){
            commentRepository.deleteById(delComment.getCommentId());
            return ResponseEntity.ok().body(new CommonResponse<>("성공적으로 삭제하였습니다."));
        }else{
            return ResponseEntity.ok().body(new ErrorResponse("자신의 댓글만 삭제할 수 있습니다."));
        }
    }
}
