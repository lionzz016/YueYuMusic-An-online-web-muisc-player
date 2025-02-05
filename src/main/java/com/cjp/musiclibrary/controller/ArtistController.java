package com.cjp.musiclibrary.controller;


import com.cjp.musiclibrary.comment.Result;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.query.ArtistPageQuery;
import com.cjp.musiclibrary.domain.query.PageQuery;
import com.cjp.musiclibrary.domain.vo.ArtistSearchVO;
import com.cjp.musiclibrary.domain.vo.ArtistVO;
import com.cjp.musiclibrary.service.artist.impl.ArtistServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cjp
 * @since 2025-01-21
 */
@RestController
@RequestMapping("/yueyumusic/artist")
@Tag(name = "艺术家相关接口")
public class ArtistController {
    @Resource
    private ArtistServiceImpl artistService;

    @PostMapping("search")
    @Operation(description = "搜索艺术家的接口，并分页显示")
    public Result<PageDTO<ArtistSearchVO>> searchArtist(@RequestBody ArtistPageQuery query) {
        return Result.success(artistService.getArtistByName(query));
    }

    @PostMapping("list")
    @Operation(description = "分页展现所有艺人")
    public Result<PageDTO<ArtistSearchVO>> listArtist(@RequestBody PageQuery query) {
        return Result.success(artistService.listArtistPage(query));
    }


    @GetMapping("details/{cid}")
    @Operation(description = "获取艺人详细的接口")
    public Result<ArtistVO> showArtist(@PathVariable("cid") String cid) {
        return Result.success(artistService.showArtistDetails(cid));
    }
}
