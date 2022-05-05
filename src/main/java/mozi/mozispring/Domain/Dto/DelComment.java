package mozi.mozispring.Domain.Dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "삭제할 댓글 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DelComment {
    @ApiModelProperty(value = "삭제할 댓글 id", notes = "삭제할 댓글 id", required = true, example="99")
    private Long commentId; // 삭제할 댓글 id
}

