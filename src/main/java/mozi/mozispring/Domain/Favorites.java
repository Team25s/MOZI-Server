package mozi.mozispring.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@ApiModel(value = "즐겨찾기")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorites")
public class Favorites {      // 즐겨찾기 도메인 객체
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "PRIVATE KEY: 즐겨찾기 id", notes = "즐겨찾기 id", required = true, example="5")
    private Long id;          // 디비 pk

    @ApiModelProperty(value = "유저 id", notes = "유저 id", required = true, example="9")
    private Long userId;      // 유저 id

    @ApiModelProperty(value = "즐겨찾기에 추가한 상대 id", notes = "즐겨찾기에 추가한 상대 id", required = true, example="43")
    private Long opponentId;  // 즐겨찾기에 추가한 상대 id
}
