package com.cjp.musiclibrary.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
public class UserExitVO {
    @JsonProperty("username")
    @Schema(description = "用户名")
    private String uName;

    @JsonProperty("last")
    @Schema(description = "最近登入时间")
    private LocalDateTime uLatesttime;

    @JsonProperty("msg")
    @Schema(description = "用户退出信息")
    private String uMsg;
}
