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
//进行搜索后调用的实体类
public class ArtistSearchVO implements Serializable {
    @JsonProperty("cid")
    @Schema(description = "艺人ID")
    private Integer cId;

    @JsonProperty("name")
    @Schema(description = "艺人名字")
    private String cName;

}
