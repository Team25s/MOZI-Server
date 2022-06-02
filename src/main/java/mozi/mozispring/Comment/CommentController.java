package mozi.mozispring.Comment;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mozi.mozispring.Domain.Comment;
import mozi.mozispring.Domain.Dto.CommentDto;
import mozi.mozispring.Domain.Dto.DelComment;
import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.UserRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentRepository commentRepository, UserRepository userRepository, CommentService commentService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentService = commentService;
    }

    /**
     * 유저 모든 댓글 불러오기
     */
    @ApiOperation(value="유저 모든 댓글 불러오기", notes="유저 모든 댓글 불러오기")
    @ApiImplicitParams(
            @ApiImplicitParam(name="id", value="유저 id", required = true, dataType="Long", paramType = "path", defaultValue = "")
    )
    @GetMapping("/comment/{id}")
    public List<Comment> getCommentController(@PathVariable("id") Long id){
        log.info("find All Comment of user");
        List<Comment> commentList = commentRepository.findAllByUserId(id);
        //List<Comment> commentList = commentRepository.findAllById(id);
        return commentList;
    }

    /**
     * 댓글 작성
     */
    @ApiOperation(value="댓글 작성하기", notes="댓글 작성하기")
    @PostMapping("/comment")
    public Comment commentController(@RequestBody CommentDto commentDto){
        Comment comment = commentRepository.save(Comment.builder()
                .userId(commentDto.getOpponentId())
                .content(commentDto.getContent())
                .build());
        return comment;
    }

    /**
     * 댓글 삭제
     */
    @ApiOperation(value="댓글 삭제하기", notes="NEED JWT IN HEADER: 댓글 삭제하기")
    @DeleteMapping("/comment")
    public DeleteDto deleteCommentController(@RequestBody DelComment delComment){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return commentService.deleteComment(delComment, findUser.get().getId());
    }
}
