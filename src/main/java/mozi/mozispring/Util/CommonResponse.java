package mozi.mozispring.Util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value="정상시 반환 값")
@Setter
@Getter
public class CommonResponse<T> extends BasicResponse{
    @ApiModelProperty(value = "데이터 개수", notes = "데이터 개수", required = true, example="1")
    private int count;
    @ApiModelProperty(value = "데이터", notes = "데이터", required = true)
    private T data;

    public CommonResponse(T data){
        this.data = data;
        if (data instanceof  List){
            this.count = ((List<T>)data).size();
        }else{
            this.count = 1;
        }
    }
}
