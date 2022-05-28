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
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id;       // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long userId;   // 유저 id
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String title;  // 일정 제목
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String location;  // 장소
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String startDate; // 시작 날짜
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String endDate;   // 종료 날짜

    @OneToMany
    @JoinColumn(name = "friends")
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private List<SimplUser> friendList = new ArrayList<>();
}
