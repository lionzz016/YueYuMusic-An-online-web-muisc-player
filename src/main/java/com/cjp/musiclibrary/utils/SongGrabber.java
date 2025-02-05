package com.cjp.musiclibrary.utils;

import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.exception.CustomException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CJP
 * @version 1.0
 */
public class SongGrabber extends AbstractGrabber<SongPO> {
    @Override
    public List<SongPO> generate(String path, Class<SongPO> clazz, String alb, String... regex) {
//        1.获取正文
        String context = getContext(path);
//        2.获取目标实体类的set方法
        List<String> methods = getMethods(clazz);
//        3.创建对象，利用反射获得的方法对字段赋值
        Map<String, List<String>> map = new HashMap<>();
        List<SongPO> pos = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < regex.length; i++) {
            String s = regex[i];
            List<String> buffer = new ArrayList<>();
            Matcher matcher = Pattern.compile(s).matcher(context);
            while (matcher.find()) {
                buffer.add(matcher.group(3));
            }
            count = buffer.size();
            map.put(String.valueOf(i), buffer);
        }
        for (int j = 0; j < count; j++) {
            SongPO songPO;
            try {
                songPO = clazz.getConstructor().newInstance();
                for (int l = 0; l < methods.size(); l++) {
                    clazz.getMethod(methods.get(l), String.class).invoke(songPO, map.get(String.valueOf(l)).get(j));
                    if (l == methods.size() - 1) {
                        clazz.getMethod(methods.get(l), Integer.class).invoke(songPO, Integer.parseInt(map.get(String.valueOf(l)).get(j)));
                    }
                }
            } catch (Exception e) {
                throw new CustomException("502", "缺少必要信息");
            }
            songPO.setSBelonged(getAlbum(getContext(path), alb).get(0));
            pos.add(songPO);
        }
        return pos;
    }

    public List<String> getAlbum(String c, String... regex) {
        List<String> list = new ArrayList<>();
        for (String s : regex) {
            Matcher matcher = Pattern.compile(s).matcher(c);
            if (matcher.find()) {
                list.add(matcher.group(3));
            }
        }
        return list;
    }

    public String get(String s) {
        return getContext(s);
    }
}

