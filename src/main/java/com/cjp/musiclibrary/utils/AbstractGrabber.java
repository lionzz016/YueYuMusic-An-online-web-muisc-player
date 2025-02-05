package com.cjp.musiclibrary.utils;

import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.exception.CustomException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
abstract public class AbstractGrabber<T> {

    abstract public List<T> generate(String path, Class<T> clazz, String alb, String... regex);

    public static String getContext(String filePath) {
        BufferedReader br = null;
        String buffer = "";
        StringBuilder context = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((buffer = br.readLine()) != null) {
                context.append(buffer);
            }
        } catch (Exception e) {
            throw new CustomException("404", "找不到文件路径");
        }
        return context.toString();
    }

    public static <T> List<String> getMethods(Class<T> clazz) {
        List<String> list = new ArrayList<>();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    list.add("set"
                            + f.getName().toUpperCase().charAt(0)
                            + f.getName().substring(1));
                }
            }
            list.remove("setSId");
            list.remove("setSBelonged");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
