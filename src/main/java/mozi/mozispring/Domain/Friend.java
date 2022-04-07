package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue
    private Long id; // 디비 pk

    private Long userId;   // 유저 id
    private Long friendId; // 친구 id
    private String mbti;   // 친구 mbti
}