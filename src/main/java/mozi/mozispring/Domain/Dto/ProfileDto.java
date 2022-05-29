package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value= "사용자 프로필 반환")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    @ApiModelProperty(value = "프로필 이미지 파일 이름", notes = "프로필 이미지 파일 이름", required = true, example="1323saf20hannqp27ry8.jpg")
    private String profileFileName;

    @ApiModelProperty(value = "프로필 이미지 링크", notes = "프로필 이미지 링크", required = true, example="http://firebase-storage/19g1pf981g2gfv3yg11f.jpg")
    private String profileFileURL; // 프로필 이미지 링크

    @ApiModelProperty(value = "유저 이름", notes = "유저 이름", required = true, example="홍길동")
    private String name;

    @ApiModelProperty(value = "mbti", notes = "mbti", required = true, example="istp")
    private String mbti;

    @ApiModelProperty(value = "유저 한줄 소개", notes = "유저 한줄 소개", required = true, example="롤 좋아하는 겜돌이입니다.")
    private String introduce;

    @ApiModelProperty(value = "유저 소개 해시태그", notes = "유저 소개 해시태그", required = true, example="#게임좋아함#밝음#말많음#외향성#부엉이#야행성")
    private String strTag;
}
