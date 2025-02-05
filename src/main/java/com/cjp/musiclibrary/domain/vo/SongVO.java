package com.cjp.musiclibrary.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SongVO implements Serializable {
    @JsonProperty("id")
    @Schema(description = "歌曲ID")
    private Integer sId;

    @JsonProperty("name")
    @Schema(description = "歌曲名称")
    private String sName;

    @JsonProperty("artist")
    @Schema(description = "艺术家")
    private String sArtist;

    @JsonProperty("duration")
    @Schema(description = "时长")
    private String sDuration;

    @JsonProperty("belonged")
    @Schema(description = "所属专辑")
    private String sBelonged;

    @JsonProperty("order")
    @Schema(description = "次序")
    private Integer sOrder;

    private static final long serialVersionUID = 1L;


}
