package com.cjp.musiclibrary.controller;

import com.cjp.musiclibrary.comment.Result;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.query.AlbumPageQuery;
import com.cjp.musiclibrary.domain.vo.AlbumListSearchVO;
import com.cjp.musiclibrary.domain.vo.AlbumSearchVO;
import com.cjp.musiclibrary.domain.vo.AlbumVO;
import com.cjp.musiclibrary.service.album.impl.AlbumServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author CJP
 * @version 1.0
 */
@RequestMapping("/yueyumusic/album")
@RestController
@Tag(name = "专辑相关接口")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumServiceImpl albumService;

    @GetMapping("details/{aid}")
    @Operation(description = "获取完整专辑信息")
    public Result<AlbumVO> getAlbum(@Parameter(name = "aid", description = "专辑ID") @PathVariable("aid") Integer id) {
        return Result.success(albumService.queryAlbumById(id));
    }

    @PostMapping("type")
    @Operation(description = "根据传入的专辑类型的参数来获取不同类型的专辑")
    public Result<PageDTO<AlbumListSearchVO>> getAlbumByType(@RequestBody AlbumPageQuery query) {
        return Result.success(albumService.getAlbumByType(query));
    }
}
