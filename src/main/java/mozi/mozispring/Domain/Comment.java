package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlIDREF;

@ApiModel(value = "밸런스 게임 응답")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {       // 댓글 도메인 객체
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "게임 id", notes = "응답한 문항의 id 를 입력해주세요", required = true, example="3")
    private Long id;         // 디비 pk

    private Long userId;     // 댓글이 달린 유저의 id
    private String content;  // 댓글 내용
}
