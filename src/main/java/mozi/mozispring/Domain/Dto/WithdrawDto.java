package mozi.mozispring.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawDto {
    private Long moment;   // 남아있는 게시글 수
    private Long comment;  // 남아있는 댓글 수
    private Long schedule; // 남아있는 일정 수
}
