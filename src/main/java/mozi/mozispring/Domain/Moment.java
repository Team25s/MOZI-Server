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

    private String date;        // 작성 날짜
    private Long userId;        // 작성자 유저 id
    private String content;     // 내용

    @OneToMany
    @JoinColumn(name = "momentPhotos")
    private List<MomentPhoto> fileNameList = new ArrayList<>();
}
