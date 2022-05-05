package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="모먼트 기록")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MomentDto {
    @ApiModelProperty(value = "모먼트 제목", notes = "모먼트 제목", required = true, example="한강 나들이")
    private String title;

    @ApiModelProperty(value = "모먼트 내용", notes = "모먼트 내용", required = true, example="재밌었습니다.")
    private String content;

    @ApiModelProperty(value = "생성 날짜", notes = "생성 날짜 - 자유형식임.", required = true, example="2022-05-05 오전8시")
    private String date;

    @ApiModelProperty(value = "작성자 id", notes = "작성자 id", required = true, example="7")
    private Long userId;

    @ApiModelProperty(value = "해시태그", notes = "해시태그는 하나의 문자열로 취급함. #을 기준으로 태그별 구분", required = true, example="#날씨좋음#한강#재밌다#조깅#나들이")
    private String hashTag;

    @ApiModelProperty(value = "첨부되는 이미지들", notes = "서버에서 MultipartFile의 List로 받게됨.", required = true)
    private List<MultipartFile> multipartFiles = new ArrayList<>();
}
