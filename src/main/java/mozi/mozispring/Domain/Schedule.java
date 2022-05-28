package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "일정")
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
    @ApiModelProperty(value = "일정 id", notes = "일정 id", required = true, example="4")
    private Long id;       // 디비 pk

    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="9")
    private Long userId;   // 유저 id

    @ApiModelProperty(value = "일정 제목", notes = "일정 제목", required = true, example="종강하고 정모")
    private String title;  // 일정 제목

    @ApiModelProperty(value = "장소", notes = "장소", required = true, example="이태원")
    private String location;  // 장소

    @ApiModelProperty(value = "시작 날짜", notes = "시작 날짜", required = true, example="2022-06-13")
    private String startDate; // 시작 날짜

    @ApiModelProperty(value = "종료 날짜", notes = "종료 날짜", required = true, example="한명쓰러질때까지")
    private String endDate;   // 종료 날짜

    @OneToMany
    @JoinColumn(name = "friends")
    @ApiModelProperty(value = "참여 친구 목록", notes = "참여 친구 목록", required = true)
    private List<SimplUser> friendList = new ArrayList<>();
}
