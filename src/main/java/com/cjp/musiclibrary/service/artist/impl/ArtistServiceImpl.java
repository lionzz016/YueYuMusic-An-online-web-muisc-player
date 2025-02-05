package com.cjp.musiclibrary.service.artist.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.AlbumPO;
import com.cjp.musiclibrary.domain.po.ArtistPO;
import com.cjp.musiclibrary.domain.query.ArtistPageQuery;
import com.cjp.musiclibrary.domain.query.PageQuery;
import com.cjp.musiclibrary.domain.vo.AlbumSearchVO;
import com.cjp.musiclibrary.domain.vo.ArtistSearchVO;
import com.cjp.musiclibrary.domain.vo.ArtistVO;
import com.cjp.musiclibrary.mapper.ArtistMapper;
import com.cjp.musiclibrary.service.artist.IArtistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cjp
 * @since 2025-01-21
 */
@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, ArtistPO> implements IArtistService {

    @Override
    public PageDTO<ArtistSearchVO> getArtistByName(ArtistPageQuery query) {
        String name = query.getName();

        Page<ArtistPO> page = query.toPage(new OrderItem().setAsc(true).setColumn("c_id"));
        if (StrUtil.isEmptyIfStr(name) || StrUtil.isBlankIfStr(name)) {
            page = lambdaQuery().page(page);
        } else
            page = lambdaQuery()
                    .like(name != null, ArtistPO::getCName, name)
                    .page(page);

        return PageDTO.of(page, ArtistSearchVO.class);
    }


    @Override
    public ArtistVO showArtistDetails(String cid) {
        ArtistPO po = getById(cid);
        List<AlbumPO> list = Db.lambdaQuery(AlbumPO.class)
                .eq(true, AlbumPO::getACast, po.getCName())
                .orderBy(true, false, AlbumPO::getAReleased)
                .list();

        ArtistVO vo = BeanUtil.copyProperties(po, ArtistVO.class);
        vo.setAlbums(BeanUtil.copyToList(list, AlbumSearchVO.class));
        return vo;
    }

    @Override
    public PageDTO<ArtistSearchVO> listArtistPage(PageQuery query) {
        Page<ArtistPO> page = query.toPage(new OrderItem().setColumn("c_id").setAsc(true));
        page = lambdaQuery().page(page);
        return PageDTO.of(page, ArtistSearchVO.class);
    }
}
