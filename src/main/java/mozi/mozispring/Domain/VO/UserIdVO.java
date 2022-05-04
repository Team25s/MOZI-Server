package mozi.mozispring.Domain.VO;

import lombok.Getter;

@Getter
public class UserIdVO {
    private final Long id;

    public UserIdVO(Long id) {
        this.id = id;
    }
}
