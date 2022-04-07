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


}
