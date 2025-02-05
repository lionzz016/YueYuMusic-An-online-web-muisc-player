package com.cjp.musiclibrary.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
//点击艺人后显示的详细实体类
public class ArtistVO implements Serializable {
    @JsonProperty("cid")
    @Schema(description = "艺人ID")
    private Integer cId;

    @JsonProperty("name")
    @Schema(description = "艺人名字")
    private String cName;

    @JsonProperty("dist")
    @Schema(description = "地区")
    private String cDist;

    @JsonProperty("birth")
    @Schema(description = "生日")
    private String cBirth;

    @JsonProperty("albums")
    @Schema(description = "专辑和单曲")
    private List<AlbumSearchVO> albums;

}
