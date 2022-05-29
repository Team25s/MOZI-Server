package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="반환할 친구 요약 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRetDto {
    @ApiModelProperty(value = "친구 id", notes = "친구 id", required = true, example="3")
    private Long friendId; // 친구 id

    @ApiModelProperty(value = "친구 mbti", notes = "친구 mbti", required = true, example="istp")
    private String mbti;   // 친구 mbti

    @ApiModelProperty(value = "친구 이름", notes = "친구 이름", required = true, example="김모지")
    private String name; // 친구 이름

    @ApiModelProperty(value = "친구 프로필 이미지 파일 이름", notes = "친구 프로필 이미지 파일 이름", required = true, example="1anwqna25eqwerqqpb1gtvjq5692rhgb1.jpg")
    private String filename; // 친구 프로필 이미지 파일 이름
}
