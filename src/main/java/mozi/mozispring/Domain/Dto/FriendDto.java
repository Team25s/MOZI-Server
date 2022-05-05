package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="친구 추가")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {
    @ApiModelProperty(value = "추가할 유저의 id", notes = "추가할 유저의 id", required = true, example="996")
    private Long friendId; // 추가할 유저의 id

    @ApiModelProperty(value = "추가할 유저의 mbti", notes = "추가할 유저의 mbti", required = true, example="entj")
    private String mbti;   // 추가할 유저의 mbti
}
