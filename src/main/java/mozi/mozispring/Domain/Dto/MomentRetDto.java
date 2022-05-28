package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="유저의 모먼트 반환 정보")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MomentRetDto {
    @ApiModelProperty(value = "모먼트 제목", notes = "모먼트 제목", required = true, example="정기모임")
    private String title;

    @ApiModelProperty(value = "모먼트 내용", notes = "모먼트 내용", required = true, example="5")
    private String content;

    @ApiModelProperty(value = "날짜", notes = "날짜", required = true, example="2022-05-28")
    private String date;

    @ApiModelProperty(value = "작성자 id", notes = "작성자 id", required = true, example="45")
    private Long userId;

    @ApiModelProperty(value = "해시태그", notes = "해시태그", required = true, example="#한강#여름#친구랑#방학#저녁#카페")
    private String hashTag;

    @ApiModelProperty(value = "첨부 파일 이름 리스트", notes = "첨부 파일 이름 리스트", required = true, example="")
    private List<String> fileNameList = new ArrayList<>();
}
