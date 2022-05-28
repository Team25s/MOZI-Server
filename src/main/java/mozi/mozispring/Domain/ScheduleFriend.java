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
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id;         // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long scheduleId; // 스케쥴 id
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long userId;     // 친구 id
}
