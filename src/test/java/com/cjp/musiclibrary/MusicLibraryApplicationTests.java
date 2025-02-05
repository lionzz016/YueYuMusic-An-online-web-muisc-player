package com.cjp.musiclibrary;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.interfaces.Claim;
import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.domain.vo.SongSearchVO;
import com.cjp.musiclibrary.service.song.impl.SongServiceImpl;
import com.cjp.musiclibrary.service.user.impl.UserServiceImpl;
import com.cjp.musiclibrary.utils.JwtUtil;
import com.cjp.musiclibrary.utils.SongGrabber;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class MusicLibraryApplicationTests {

    @Resource
    private SongServiceImpl songService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void saveSongs() {
        Field[] fields = SongPO.class.getDeclaredFields();
        for (Field f : fields) {
            if (!Modifier.isStatic(f.getModifiers())) {
                System.out.println(f.getName());
            }
        }

    }

    @Test
    void getMethods() {
        try {
            SongPO songPO = new SongPO();
            Method method = SongPO.class.getMethod("setSName", String.class);
            method.invoke(songPO, "1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void grab() {
        List<String> methods = SongGrabber.getMethods(SongPO.class);
//        for (String s : methods) {
//            System.out.println(s);
//        }
        for (int i = 0; i < methods.size(); i++) {
            System.out.println(methods.get(i));
        }
    }

    @Test
    void test1() {
        Map<String, List<SongPO>> map = new HashMap<>();
        String context = SongGrabber.getContext("D:\\Develop\\spring\\music-library\\src\\main\\resources\\target.txt");

//        Matcher e = Pattern.compile("(songlist__songname_txt)([\"<= >a-z]+)([ ().a-zA-Z\\u4e00-\\u9fa5]+)").matcher(context);
//        Matcher e = Pattern.compile("(playlist__author)([\"<= >a-z]+)([ ().a-zA-Z\\u4e00-\\u9fa5]+)").matcher(context);
//        Matcher e = Pattern.compile("(songlist__time)([\"<= >a-z]+)([0-9:]+)").matcher(context);
        Matcher e = Pattern.compile("(songlist__number)([\"<=\\- >a-z]+)([ ().a-zA-Z\\u4e00-\\u9fa5]+)").matcher(context);
//        Matcher e = Pattern.compile("(data__name_txt)([\"<=\\- >a-z]+)([0-9]+)").matcher(context);
        List<SongPO> pos = new ArrayList<>();
        while (e.find()) {
            SongPO songPO = new SongPO();
            songPO.setSOrder(e.group(3));
            pos.add(songPO);
//            System.out.println(e.group(3));
        }
        map.put("setSOrder", pos);
        for (Map.Entry<String, List<SongPO>> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

//        System.out.println(map);

    }


    @Test
    void record() {
        List<SongPO> list = new SongGrabber().generate("D:\\Develop\\spring\\music-library\\src\\main\\resources\\target.txt",
                SongPO.class,
                "T.I.M.E.",
                "(songlist__songname_txt)([\"<= >a-z]+)([ ()（）-，。,.'0-9a-zA-Z\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(playlist__author)([\"<= >a-z]+)([ ()（），。,.'a-zA-Z0-9:\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(songlist__time)([\"<= >a-z]+)([0-9:]+)",
                "(songlist__number)([\"<=\\- >a-z]+)([0-9]+)");
        System.out.println(songService.addSongs(list) ? "添加成功" : "添加失败");
    }

    @Test
    void test2() {
        String path = "D:\\Develop\\spring\\music-library\\src\\main\\resources\\target.txt";

        List<SongPO> list = new SongGrabber().generate(
                path,
                SongPO.class,
                "(data__name_txt)([\"<= >a-z]+)([ ()（）-，。,.'0-9a-zA-Z\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(songlist__songname_txt)([\"<= >a-z]+)([ ()（）-，。,.'0-9a-zA-Z\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(playlist__author)([\"<= >a-z]+)([ ()（），。,.'a-zA-Z0-9:\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(songlist__time)([\"<= >a-z]+)([0-9:]+)",
                "(songlist__number)([\"<=\\- >a-z]+)([0-9]+)");
        System.out.println(songService.addSongs(list) ? "添加成功" : "添加失败");
    }

    @Test
    void test3() {
        String s = """
                """;
        List<String> album = new SongGrabber().getAlbum(s,
                "(语种：\n +)(<!-- -->\n +)([\\u4e00-\\u9fa5]+)",
                "(发行时间：\n +)(<!-- -->\n +)([0-9-]+)",
                "(唱片公司：\n +)(<!-- -->\n +)([a-zA-Z0-9-.,\\uac00-\\ud7a3\\u3040-\\u30ff\\u4e00-\\u9fa5]+)",
                "(类型：\n +)(<!-- -->\n +)([\\u4e00-\\u9fa5]+)");
        for (String s1 : album) {
            System.out.println(s1);
        }
    }

    @Test
    void test4() {
        String generate = JwtUtil.generate(33L);
        Map<String, Claim> claimMap = JwtUtil.verify(generate);
        Claim user = claimMap.get("user");
        Map<String, Object> map = user.asMap();
        System.out.println(map.get("Id"));
    }

    @Test
    void redisTest() {
        stringRedisTemplate.delete("music:user:1");
    }

    @Test
    void testToken() {
        String generate = JwtUtil.generate(2023L);
        System.out.println(generate);
        System.out.println("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7IklkIjoyMDIzfSwiZXhwIjoxNzM4NDg3MDU1fQ.3Kh6jVqzFZziC-RyXNvzmnhmjeawQY2e0Kyf7OhpgxQ"
                .equals(generate));
    }

    @Test
    void Test6() {
        String s1 = "蔡依林/邓紫棋";
        String s2 = "邓紫棋";

        List<String> list = Arrays.stream(s1.split("/")).toList();
        List<String> list1 = Arrays.stream(s2.split("/")).toList();
        System.out.println(list);
        System.out.println(list1);
        System.out.println(songService.getSong(184));
    }

    @Test
    void test7() {
        System.out.println(songService.listSong(109, 110, 113, 112));
    }
}

