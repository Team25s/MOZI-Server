package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="삭제 여부 리턴")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDto {
    @ApiModelProperty(value = "삭제 여부", notes = "삭제 여부", required = true, example="true")
    private boolean deleted;

    @ApiModelProperty(value = "삭제 메시지", notes = "삭제 메시지", required = true, example="정상적으로 삭제되었습니다.")
    private String message;
}
