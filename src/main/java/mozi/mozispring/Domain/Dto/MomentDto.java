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
public class MomentDto {
    private String title;
    private String content;
    private String date;
    private Long userId;
    private String hashTag;
    private List<MultipartFile> multipartFiles = new ArrayList<>();
}
