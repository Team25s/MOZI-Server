package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "친구")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "친구 id", notes = "친구 id", required = true, example="9")
    private Long id; // 디비 pk

    @ApiModelProperty(value = "사용자 id", notes = "사용자 id", required = true, example="43")
    private Long userId;   // 유저 id

    @ApiModelProperty(value = "추가할 친구 id", notes = "추가할 친구 id", required = true, example="89")
    private Long friendId; // 친구 id

    @ApiModelProperty(value = "친구 mbti", notes = "친구 mbti", required = true, example="esfp")
    private String mbti;   // 친구 mbti

    @ApiModelProperty(value = "노크 횟수", notes = "노크 횟수", required = true, example="0")
    private int knock;     // 노크 횟수
}
