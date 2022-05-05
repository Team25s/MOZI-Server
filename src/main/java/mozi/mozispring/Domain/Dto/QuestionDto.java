package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="밸런스 게임 문항")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    @ApiModelProperty(value = "밸런스 게임 문항 제목", notes = "밸런스 게임 문항 제목", required = true, example="나는 인간극장을 보고 눈물을 흘린 적이 있다.")
    private String question; // 문항 제목
}
