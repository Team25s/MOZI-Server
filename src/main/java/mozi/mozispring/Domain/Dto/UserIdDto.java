package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value="유저 id")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdDto {
    @ApiModelProperty(value = " 유저 id ", notes = " 유저 id ", required = true, example="26")
    private Long id; // 유저 id
}
