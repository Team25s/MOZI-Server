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
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id;          // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String email;     // 유저 이메일
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String name;      // 유저 이름
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String mbti;      // 유저 mbti
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String profileFilename;  // 프로필 파일명
}
