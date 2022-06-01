package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel(value = "밸런스 게임 도메인 객체")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameQA {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "PRIVATE KEY: GameQA id", notes = "GameQA id", required = true, example="8")
    private Long id;

    @ApiModelProperty(value = "밸런스 게임 질문", notes = "밸런스 게임 질문", required = true, example="나는 샤워를 할때 머리부터 감는다.")
    private String question;

    @ApiModelProperty(value = "긍정 응답", notes = "긍정 응답", required = true, example="true")
    private int positive_answer;

    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private int negative_answer;
}
