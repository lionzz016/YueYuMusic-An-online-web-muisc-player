package com.cjp.musiclibrary.domain.dto;

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
@Tag(name = "用户注销实体类")
public class UserExitFormDTO {
    @Schema(description = "用户Id")
    private Long uId;
}
