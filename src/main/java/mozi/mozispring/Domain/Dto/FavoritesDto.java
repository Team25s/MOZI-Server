package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@ApiModel(value="즐겨찾기")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesDto { // 즐겨찾기 Dto
    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="5")
    private Long userId; // 유저 id

    @ApiModelProperty(value = "즐겨찾기에 추가할 상대 id", notes = "즐겨찾기에 추가할 상대 id", required = true, example="8")
    private Long opponentId; // 즐겨찾기에 추가할 상대 id
}
