package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel(value = "모먼트 첨부 사진")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MomentPhoto {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "PRIVATE KEY: 모먼트 첨부 사진 id", notes = "모먼트 첨부 사진 id", required = true, example="8")
    private Long id;         // 디비 pk

    @ApiModelProperty(value = "해당 moment id", notes = "해당 moment id", required = true, example="98")
    private Long momentId;   // 해당 moment id

    @ApiModelProperty(value = "모먼트에 첨부된 사진 이름", notes = "모먼트에 첨부된 사진 이름", required = true, example="19g1pf981g2gfv3yg11f.jpg")
    private String fileName; // 모먼트에 첨부된 사진 이름
}
