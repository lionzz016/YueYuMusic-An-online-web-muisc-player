package com.cjp.musiclibrary.service.album;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.AlbumPO;
import com.cjp.musiclibrary.domain.query.AlbumPageQuery;
import com.cjp.musiclibrary.domain.vo.AlbumListSearchVO;
import com.cjp.musiclibrary.domain.vo.AlbumSearchVO;
import com.cjp.musiclibrary.domain.vo.AlbumVO;

/**
 * @author CJP
 * @version 1.0
 */
public interface IAlbumService extends IService<AlbumPO> {
    AlbumVO queryAlbumById(Integer id);

    PageDTO<AlbumListSearchVO> getAlbumByType(AlbumPageQuery query);
}