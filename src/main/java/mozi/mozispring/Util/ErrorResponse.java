package mozi.mozispring.Util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value="에러시 반환 값")
@Getter
@Setter
public class ErrorResponse extends BasicResponse{
    @ApiModelProperty(value = "에러 메시지", notes = "에러 메시지", required = true, example="자신의 일정만 수정할 수 있습니다.")
    private String message;
    
    public ErrorResponse(String message) {
        this.message = message;
    }
}

