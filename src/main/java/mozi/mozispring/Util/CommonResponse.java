package mozi.mozispring.Util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommonResponse<T> extends BasicResponse{
    private int count;
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
