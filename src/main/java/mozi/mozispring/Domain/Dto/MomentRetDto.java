package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
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
    private String title;
    private String content;
    private String date;
    private Long userId;
    private String hashTag;
    private List<String> fileNameList = new ArrayList<>();
}
