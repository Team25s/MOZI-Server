package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel(value = "게임 로그")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameLog {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "PRIVATE KEY: 게임 로그 id", notes = "게임 로그 id", required = true, example="3")
    private Long id; // 디비 pk

    @ApiModelProperty(value = "밸런스 게임 id", notes = "밸런스 게임 id", required = true, example="8")
    private Long questionId; // 밸런스 게임 id

    /**
     * 긍정 응답
     */
    private int esfp_positive;
    private int entp_positive;
    private int enfj_positive;
    private int enfp_positive;
    private int esfj_positive;
    private int entj_positive;
    private int estj_positive;
    private int estp_positive;
    private int infp_positive;
    private int infj_positive;
    private int isfp_positive;
    private int isfj_positive;
    private int intj_positive;
    private int istp_positive;
    private int istj_positive;
    private int intp_positive;
    private int xxxx_positive; // 익명 - 비공개

    /**
     * 부정 응답
     */
    private int esfp_negative;
    private int entp_negative;
    private int enfj_negative;
    private int enfp_negative;
    private int esfj_negative;
    private int entj_negative;
    private int estj_negative;
    private int estp_negative;
    private int infp_negative;
    private int infj_negative;
    private int isfp_negative;
    private int isfj_negative;
    private int intj_negative;
    private int istp_negative;
    private int istj_negative;
    private int intp_negative;
    private int xxxx_negative; // 익명 - 비공개
}
