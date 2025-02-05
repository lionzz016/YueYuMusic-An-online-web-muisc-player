package com.cjp.musiclibrary.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AlbumVO implements Serializable {
    @JsonProperty("id")
    @Schema(description = "专辑ID")
    private Integer aId;

    @JsonProperty("name")
    @Schema(description = "专辑名称")
    private String aName;

    @JsonProperty("producer")
    @Schema(description = "制作方")
    private String aProducer;

    @JsonProperty("artist")
    @Schema(description = "制作人")
    private ArtistSearchVO aCaster;

    @JsonProperty("released")
    @Schema(description = "发行时间")
    private String aReleased;

    @JsonProperty("type")
    @Schema(description = "类型")
    private String aType;

    @JsonProperty("sort")
    @Schema(description = "分类")
    private String aSort;

    @JsonProperty("songs")
    @Schema(description = "包含歌曲")
    private List<SongVO> songList;


    private static final long serialVersionUID = 1L;

}
