package com.cjp.musiclibrary.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author CJP
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum UserEnum {
    NORMAL(false,"普通用户"),
    VIP(true,"超级用户");

    @EnumValue
    private final Boolean status;
    @JsonValue
    private final String desc;

}
