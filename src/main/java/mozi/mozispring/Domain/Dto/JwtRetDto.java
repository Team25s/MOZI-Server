package mozi.mozispring.Domain.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "jwt 반환")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRetDto {
    @ApiModelProperty(value = "메시지", notes = "메시지", required = true, example="유효한 jwt를 발급하였습니다.")
    private String message; // 메시지

    @ApiModelProperty(value = "jwt", notes = "jwt ", required = true, example="bga392ygdhn8o7otty7834fgty9k2049o87n243i8td7yoeigwoi87ert4510248jtidwejygisuyrg")
    private String jwt;  // jwt

    @ApiModelProperty(value = "나의 userId", notes = "나의 userId ", required = true, example="3")
    private Long myId; // 나의 userId
}
