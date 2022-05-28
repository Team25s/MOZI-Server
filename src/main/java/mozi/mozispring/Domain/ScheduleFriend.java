package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "일정 참여 친구")
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
    @ApiModelProperty(value = "일정 참여 id", notes = "일정 참여 id", required = true, example="2")
    private Long id;         // 디비 pk

    @ApiModelProperty(value = "스케쥴 id", notes = "스케쥴 id", required = true, example="78")
    private Long scheduleId; // 스케쥴 id

    @ApiModelProperty(value = "일정 참여 친구 id", notes = "일정 참여 친구 id", required = true, example="87")
    private Long userId;     // 친구 id
}
