package mozi.mozispring.Domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "모먼트")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moment")
public class Moment {           // 모먼트 도메인 객체
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id;            // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String title;       // 모먼트 제목
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String content;     // 내용
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String date;        // 작성 날짜
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long userId;        // 작성자 유저 id
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String hashTag;     // 해시태그
}
