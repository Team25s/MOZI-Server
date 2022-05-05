package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("삭제할 일정 정보")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDelDto {
    @ApiModelProperty(value = "Schedule 데이버베이스 pk"
            , notes = "Schedule 데이버베이스 pk 입니다. Schedule 정보를 받았을 경우의 해당 정보의 id에 해당하는 정보입니다."
            , required = true
            , example="8")
    private Long id;          // Schedule 데이버베이스 pk

    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="9")
    private Long userId;      // 유저 id
}
