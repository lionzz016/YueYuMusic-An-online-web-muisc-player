package com.cjp.musiclibrary.service.song;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.domain.query.SongPageQuery;
import com.cjp.musiclibrary.domain.vo.SongListVO;
import com.cjp.musiclibrary.domain.vo.SongSearchVO;

import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
public interface ISongService extends IService<SongPO> {
    PageDTO<SongSearchVO> querySongByName(SongPageQuery songPageQuery);


    Boolean addSongs(List<SongPO> list);

    List<SongListVO> listSpecial();

    List<SongListVO> listNew();

    List<SongListVO> listRecommend();

}
