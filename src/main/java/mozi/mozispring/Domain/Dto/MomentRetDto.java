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
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private String title;
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private String content;
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private String date;
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private Long userId;
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private String hashTag;
    @ApiModelProperty(value = "댓글 id", notes = "Comment의 데이터베이스 private key입니다.", required = true, example="5")
    private List<String> fileNameList = new ArrayList<>();
}
