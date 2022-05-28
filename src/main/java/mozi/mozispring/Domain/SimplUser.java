package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel(value = "사용자 요약 정보")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplUser {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "사용자 요약 정보 id", notes = "사용자 요약 정보 id", required = true, example="77")
    private Long id;          // 디비 pk

    @ApiModelProperty(value = "유저 이메일", notes = "유저 이메일", required = true, example="mozi25@gmail.com")
    private String email;     // 유저 이메일

    @ApiModelProperty(value = "유저 이름", notes = "유저 이름", required = true, example="김모지")
    private String name;      // 유저 이름

    @ApiModelProperty(value = "유저 mbti", notes = "유저 mbti", required = true, example="istp")
    private String mbti;      // 유저 mbti

    @ApiModelProperty(value = "프로필 파일명", notes = "프로필 파일명", required = true, example="1hy2509go192ghr0e98ygo3v4fadsf.jpg")
    private String profileFilename;  // 프로필 파일명
}
