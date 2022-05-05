package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value="탈퇴회원 보유 콘텐츠 고지 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawDto {

    @ApiModelProperty(value = " 남아있는 게시글 수", notes = " 남아있는 게시글 수", required = true, example="4")
    private Long moment;   // 남아있는 게시글 수

    @ApiModelProperty(value = " 남아있는 댓글 수 ", notes = " 남아있는 댓글 수 ", required = true, example="85")
    private Long comment;  // 남아있는 댓글 수

    @ApiModelProperty(value = " 남아있는 일정 수 ", notes = " 남아있는 일정 수 ", required = true, example="3")
    private Long schedule; // 남아있는 일정 수
}
