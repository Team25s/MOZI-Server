package mozi.mozispring.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDelDto {
    private Long id;          // 스케쥴 도메인 객체 id
    private Long userId;      // 유저 id
}
