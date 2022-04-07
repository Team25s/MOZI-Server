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
@Table(name = "tag")
public class Tag {   // 태그 도메인 객체
    @Id
    @GeneratedValue
    private Long id; // 디비 pk

    private Long momentId;  // 모먼트 id
    private String content; // 태그명
}
