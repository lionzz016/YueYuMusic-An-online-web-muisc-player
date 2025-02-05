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
@Tag(name = "歌曲查询实体")
public class SongPageQuery extends PageQuery{
    @Schema(description = "需要查询的歌曲名")
    private String name;
}
