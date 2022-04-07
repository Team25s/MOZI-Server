package mozi.mozispring.Domain.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DelComment {
    private Long commentId; // 삭제할 댓글 id
}

