package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplUser {
    @Id
    @GeneratedValue
    private Long id;          // 디비 pk

    private String email;     // 유저 이메일
    private String name;      // 유저 이름
    private String mbti;      // 유저 mbti
    private String profileFilename;  // 프로필 파일명
}