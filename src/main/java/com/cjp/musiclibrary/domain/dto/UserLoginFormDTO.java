package com.cjp.musiclibrary.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "用户登录表单实体")
public class UserLoginFormDTO implements Serializable {
    @Schema(description = "用户名")
    @Pattern(regexp = "^\\S{6,16}$")
    private String uName;

    @Schema(description = "用户密码")
    @Pattern(regexp = "^\\S{3,20}$")
    private String uPassword;


}
