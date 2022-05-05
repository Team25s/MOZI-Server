package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "삭제할 노크 정보")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KnockDelDto {
    @ApiModelProperty(value = "노크 id", notes = "노크 id", required = true, example="7")
    private Long knockId;
}
