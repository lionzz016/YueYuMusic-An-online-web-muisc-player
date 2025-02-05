package com.cjp.musiclibrary.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.cjp.musiclibrary.enums.UserEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 *
 * </p>
 *
 * @author cjp
 * @since 2025-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@Tag(name = "用户持久层实体类")
public class UserPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uId;

    private String uName;


    private String uPassword;

    private String uEmail;

    private String uGender;

    private String uBirth;

    private UserEnum uStatus;

    private LocalDateTime uLatesttime;

    private LocalDateTime uCreatetime;


}
