package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "밸런스 게임 응답")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    @ApiModelProperty(value = "게임 id", notes = "응답한 문항의 id 를 입력해주세요", required = true, example="3")
    private Long questionId;

    @ApiModelProperty(value = "예", notes = "예라고 응답했을 경우", required = true, example="true")
    private boolean positive_answer;

    @ApiModelProperty(value = "아니오", notes = "아니오라고 응답했을 경우", required = true, example="false")
    private boolean negative_answer;
}
