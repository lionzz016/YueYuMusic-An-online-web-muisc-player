package com.cjp.musiclibrary.controller;

import com.cjp.musiclibrary.comment.Result;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.query.SongPageQuery;
import com.cjp.musiclibrary.domain.vo.SongListVO;
import com.cjp.musiclibrary.domain.vo.SongSearchVO;
import com.cjp.musiclibrary.service.song.impl.SongServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@RequestMapping("/yueyumusic/song")
@RestController
@Tag(name = "歌曲相关接口")
public class SongController {
    @Resource
    private SongServiceImpl songService;

    @PostMapping("search")
    @Operation(description = "搜索歌曲")
    public Result<PageDTO<SongSearchVO>> searchSong(@RequestBody SongPageQuery songPageQuery) {
        return Result.success(songService.querySongByName(songPageQuery));
    }

    @GetMapping("special")
    @Operation(description = "新年特别歌单")
    public Result<List<SongListVO>> listSpecial() {
        return Result.success(songService.listSpecial());
    }

    @GetMapping("new")
    @Operation(description = "新歌推荐歌单")
    public Result<List<SongListVO>> listNew() {
        return Result.success(songService.listNew());
    }

    @GetMapping("recommend")
    @Operation(description = "特别推荐歌单")
    public Result<List<SongListVO>> listRecommend() {
        return Result.success(songService.listRecommend());
    }
}
