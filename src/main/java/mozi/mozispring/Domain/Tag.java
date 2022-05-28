package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "태그")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {   // 태그 도메인 객체
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long id; // 디비 pk
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private Long momentId;  // 모먼트 id
    @ApiModelProperty(value = "부정 응답", notes = "부정 응답", required = true, example="false")
    private String content; // 태그명
}
