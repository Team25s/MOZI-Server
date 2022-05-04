package mozi.mozispring.Domain.Dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private Long   userId;    // 유저 테이블 pk
    private String email;     // 유저 이메일
    private String name;      // 유저 이름
    private String mbti;      // 유저 mbti
    private String profileFilename;  // 프로필 파일명
}
