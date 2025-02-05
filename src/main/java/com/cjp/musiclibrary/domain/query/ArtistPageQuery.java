package com.cjp.musiclibrary.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "艺术家查询实体")
public class ArtistPageQuery extends PageQuery {
    @Schema(description = "查询的艺术家名字")
    private String name;
}
