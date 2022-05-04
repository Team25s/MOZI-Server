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
public class MomentPhoto {

    @Id
    @GeneratedValue
    private Long id;     // 디비 pk

    private Long momentId;
    private String fileName;
}