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
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id;         // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long momentId;   // 해당 moment id
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String fileName; // 모먼트에 첨부된 사진 이름
}
