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
@Table(name = "schedule")
public class Schedule {    // 일정 도메인 객체
    @Id
    @GeneratedValue
    private Long id;       // 디비 pk

    private Long userId;   // 유저 id

    private String title;  // 일정 제목
    private String location;  // 장소
    private String startDate; // 시작 날짜
    private String endDate;   // 종료 날짜

    @OneToMany
    @JoinColumn(name = "friends")
    private List<SimplUser> friendList = new ArrayList<>();
}
