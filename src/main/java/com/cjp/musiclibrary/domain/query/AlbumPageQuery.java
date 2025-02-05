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
@Tag(name = "专辑分页实体类")
public class AlbumPageQuery extends PageQuery {
    @Schema(description = "专辑类型")
    private String type;
}
