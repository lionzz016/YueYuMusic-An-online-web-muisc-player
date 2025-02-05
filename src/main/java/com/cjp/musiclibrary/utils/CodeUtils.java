package com.cjp.musiclibrary.utils;

/**
 * @author CJP
 * @version 1.0
 */
//邀请码校验工具
public class CodeUtils {

    private static final String[] s = {
            "FsJj5j",
            "dYjVVC",
            "9VCXUT",
            "5SJCPV",
            "J2kRcg",
            "TbgUhF",
            "SbMcbK",
            "EXyZVu",
            "K4Thnb",
            "cpP8tg"
    };

    public static Boolean explain(String code) {
        for (String s1 : s) {
            if (code.equals(s1)) {
                return true;
            }
        }
        return false;
    }
}
