package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@ApiModel(value = "유저")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {           // 유저 도메인 객체
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="66")
    private Long id;          // 디비 pk

    @ApiModelProperty(value = "유저 이메일", notes = "유저 이메일", required = true, example="mozi25@gmail.com")
    private String email;     // 유저 이메일

    @ApiModelProperty(value = "유저 비밀번호", notes = "유저 비밀번호", required = true, example="mozi0034!!3a")
    private String password;  // 유저 비밀번호

    @ApiModelProperty(value = "유저 이름", notes = "유저 이름", required = true, example="김모지")
    private String name;      // 유저 이름

    @ApiModelProperty(value = "유저 한줄 소개", notes = "유저 한줄 소개", required = true, example="김밥을 좋아하는 김모지입니다.")
    private String introduce; // 유저 한줄 소개

    @ApiModelProperty(value = "해시태그", notes = "해시태그", required = true, example="#롤#학생#프로게이머지망생#야행성")
    private String strTag;   // 해시태그

    @ApiModelProperty(value = "유저 mbti", notes = "유저 mbti", required = true, example="isfp")
    private String mbti;      // 유저 mbti

    @ApiModelProperty(value = "프로필 파일명", notes = "프로필 파일명", required = true, example="28tgj9dj374tf1yv123512fftyr694jhg.jpg")
    private String profileFilename;  // 프로필 파일명

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
