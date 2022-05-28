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
    @ApiModelProperty(value = "PRIVATE KEY: 모먼트 id", notes = "모먼트 id", required = true, example="5")
    private Long id;            // 디비 pk

    @ApiModelProperty(value = "모먼트 제목", notes = "모먼트 제목", required = true, example="종강하고 정모")
    private String title;       // 모먼트 제목

    @ApiModelProperty(value = " 내용", notes = " 내용", required = true, example="한강에서 치맥")
    private String content;     // 내용

    @ApiModelProperty(value = "작성 날짜", notes = "작성 날짜", required = true, example="2022-06-13")
    private String date;        // 작성 날짜

    @ApiModelProperty(value = "작성자 유저 id", notes = "작성자 유저 id", required = true, example="8")
    private Long userId;        // 작성자 유저 id

    @ApiModelProperty(value = "해시태그", notes = "해시태그", required = true, example="#한강#치맥#종강")
    private String hashTag;     // 해시태그
}
