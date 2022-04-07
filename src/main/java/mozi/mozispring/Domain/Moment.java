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
@Table(name = "moment")
public class Moment {           // 모먼트 도메인 객체
    @Id
    @GeneratedValue
    private Long id;            // 디비 pk

    private String date;        // 작성 날짜
    private Long userId;        // 작성자 유저 id
    private String content;     // 내용
    private String fireBaseUrl; // 파이어베이스 이미지 url
}
