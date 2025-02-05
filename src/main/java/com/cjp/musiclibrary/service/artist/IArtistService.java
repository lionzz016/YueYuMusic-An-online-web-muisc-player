package com.cjp.musiclibrary.service.artist;

import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.ArtistPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.musiclibrary.domain.query.ArtistPageQuery;
import com.cjp.musiclibrary.domain.query.PageQuery;
import com.cjp.musiclibrary.domain.vo.ArtistSearchVO;
import com.cjp.musiclibrary.domain.vo.ArtistVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cjp
 * @since 2025-01-21
 */
public interface IArtistService extends IService<ArtistPO> {


    PageDTO<ArtistSearchVO> getArtistByName(ArtistPageQuery query);

    ArtistVO showArtistDetails(String cid);

    PageDTO<ArtistSearchVO> listArtistPage(PageQuery query);
}
