package mozi.mozispring.Domain.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogInDto {
    private String email;
    private String password;
}
