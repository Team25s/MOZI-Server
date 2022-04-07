package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {           // 유저 도메인 객체
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;          // 디비 pk

    private String email;     // 유저 이메일
    private String password;  // 유저 비밀번호
    private String name;      // 유저 이름
    private String mbti;      // 유저 mbti
    private String introduce; // 유저 한줄 소개
}
