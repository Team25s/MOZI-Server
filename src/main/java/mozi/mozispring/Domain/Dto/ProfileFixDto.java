package mozi.mozispring.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileFixDto {
    private MultipartFile multipartFile;
    private String name;
    private String mbti;
    private String introduce;
    private String strTag;
}
