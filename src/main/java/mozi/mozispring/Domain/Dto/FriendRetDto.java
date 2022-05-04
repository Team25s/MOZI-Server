package mozi.mozispring.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRetDto {
    private Long friendId; // 추가할 유저의 id
    private String mbti;   // 추가할 유저의 mbti
    private String filename; // 친구 프로필 이미지 파일 이름
}
