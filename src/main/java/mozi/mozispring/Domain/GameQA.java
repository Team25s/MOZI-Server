package mozi.mozispring.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameQA {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private int positive_answer;
    private int negative_answer;
}
