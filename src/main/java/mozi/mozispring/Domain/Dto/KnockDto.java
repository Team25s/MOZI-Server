package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "노크 보내기")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KnockDto {
    @ApiModelProperty(value = "상대방 id", notes = "상대방 id", required = true, example="3")
    private Long opponentId;  // 상대방 id
}
