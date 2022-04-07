package mozi.mozispring.Domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

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
    private Long id;          // 디비 pk

    private String email;     // 유저 이메일
    private String password;  // 유저 비밀번호
    private String name;      // 유저 이름
    private String mbti;      // 유저 mbti
    private String introduce; // 유저 한줄 소개

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
