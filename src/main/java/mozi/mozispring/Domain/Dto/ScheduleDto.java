package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="등록할 일정 정보")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    @ApiModelProperty(value = "일정 제목", notes = "일정 제목", required = true, example="한강에서 만나기")
    private String title;       // 일정 제목

    @ApiModelProperty(value = "장소", notes = "장소", required = true, example="한강역 1번출구")
    private String location;    // 장소

    @ApiModelProperty(value = "시작 날짜", notes = "시작 날짜", required = true, example="2022-05-05 오전 10시")
    private String startDate;   // 시작 날짜

    @ApiModelProperty(value = "종료 날짜", notes = "종료 날짜", required = true, example="2022-05-05 오후 4시")
    private String endDate;     // 종료 날짜

    @ApiModelProperty(value = "참여하는 친구들 id 리스트"
            , notes = "참여하는 친구들 id 리스트입니다. 서버단에서는 Long 타입의 ArrayList 로 취급됨."
            , required = true)
    private List<Long> friends = new ArrayList<>();    // 참여하는 친구들 id 리스트
}
