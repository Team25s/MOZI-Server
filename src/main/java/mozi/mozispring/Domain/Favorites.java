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
@Table(name = "favorites")
public class Favorites { // 즐겨찾기 도메인 객체
    @Id
    @GeneratedValue
    private Long id; // 디비 pk

    private Long userId;      // 유저 id
    private Long opponentId;  // 상대 id
}
