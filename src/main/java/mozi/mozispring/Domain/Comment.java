package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlIDREF;

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
    private Long id;         // 디비 pk

    private Long userId;     // 댓글이 달린 유저의 id
    private String content;  // 댓글 내용
}
