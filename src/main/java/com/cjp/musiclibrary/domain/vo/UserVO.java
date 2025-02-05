package com.cjp.musiclibrary.domain.vo;

import com.cjp.musiclibrary.enums.UserEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "用户视图实体类")
public class UserVO implements Serializable {
    @JsonProperty("uid")
    @Schema(description = "用户ID")
    private Long uId;

    @JsonProperty("username")
    @Schema(description = "用户名")
    private String uName;

    @JsonProperty("status")
    @Schema(description = "用户状态")
    private UserEnum uStatus;

    @JsonProperty("last")
    @Schema(description = "最近登入时间")
    private LocalDateTime uLatesttime;

    @JsonProperty("token")
    @Schema(description = "鉴权")
    private String authorization;

}
