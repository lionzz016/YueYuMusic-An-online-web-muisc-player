package com.cjp.musiclibrary.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "用户注册表单实体")
public class UserRegistryFormDTO implements Serializable {
    @JsonProperty("username")
    @Schema(description = "用户名")
    @Pattern(regexp = "^\\S{6,16}$")
    private String uName;

    @JsonProperty("password")
    @Schema(description = "用户密码")
    @Pattern(regexp = "^\\S{3,20}$")
    private String uPassword;

    @JsonProperty("email")
    @Schema(description = "用户邮箱")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+\\.com$")
    private String uEmail;

    @JsonProperty("code")
    @Schema(description = "邀请码")
    private String uCode;
}
