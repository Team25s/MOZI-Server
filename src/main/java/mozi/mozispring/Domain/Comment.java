package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "댓글")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {       // 댓글 도메인 객체
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private Long id;         // 디비 pk
    @ApiModelProperty(value = "댓글이 달린 유저의 id", notes = "댓글이 달린 유저의 id", required = true, example="8")
    private Long userId;     // 댓글이 달린 유저의 id
    @ApiModelProperty(value = "댓글 내용", notes = "댓글 내용", required = true, example="잘 구경하다 갑니다.")
    private String content;  // 댓글 내용
}
