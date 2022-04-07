package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule_friend")
public class ScheduleFriend {   // 일정에 함께하는 친구 도메인 객체
    @Id
    @GeneratedValue
    private Long id;         // 디비 pk

    private Long scheduleId; // 스케쥴 id
    private Long userId;     // 친구 id
}
