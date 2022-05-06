package mozi.mozispring.Domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    private String title;       // 모먼트 제목
    private String content;     // 내용
    private String date;        // 작성 날짜
    private Long userId;        // 작성자 유저 id
    private String hashTag;     // 해시태그
}
