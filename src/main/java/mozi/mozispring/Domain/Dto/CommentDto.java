package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "댓글")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @ApiModelProperty(value = "상대방 데이터베이스 pk", notes = "상대방 데이터베이스 pk", required = true, example="54")
    private Long opponentId; // 상대방 유저 id

    @ApiModelProperty(value = "댓글 내용", notes = "댓글 내용", required = true, example="안녕하세요 반갑습니다. ")
    private String content;  // 댓글 내용
}
