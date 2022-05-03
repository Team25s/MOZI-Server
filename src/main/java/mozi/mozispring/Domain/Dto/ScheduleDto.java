package mozi.mozispring.Domain.Dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private String title;       // 일정 제목
    private String location;    // 장소
    private String startDate;   // 시작 날짜
    private String endDate;     // 종료 날짜

    private List<Long> friends = new ArrayList<>();    // 함께하는 친구들 id
}
