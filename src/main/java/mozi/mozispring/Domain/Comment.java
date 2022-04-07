package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlIDREF;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;  // 디비 pk

    private Long writerId;  // 작성자 id
    private Long opponentId; // 상대방 id
}
