package com.cjp.musiclibrary.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
public class AlbumSearchVO implements Serializable {
    @JsonProperty("id")
    @Schema(description = "专辑ID")
    private Integer aId;

    @JsonProperty("name")
    @Schema(description = "专辑名称")
    private String aName;

    @JsonProperty("released")
    @Schema(description = "发行时间")
    private String aReleased;

    @JsonProperty("sort")
    @Schema(description = "分类")
    private String aSort;

}
