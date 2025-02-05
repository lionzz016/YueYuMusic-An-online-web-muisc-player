package com.cjp.musiclibrary.service.album.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.AlbumPO;
import com.cjp.musiclibrary.domain.po.ArtistPO;
import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.domain.query.AlbumPageQuery;
import com.cjp.musiclibrary.domain.vo.*;
import com.cjp.musiclibrary.mapper.AlbumMapper;
import com.cjp.musiclibrary.service.album.IAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, AlbumPO> implements IAlbumService {


    public AlbumVO queryAlbumById(Integer id) {
//        1.获取实体对象
        AlbumPO albumPO = getById(id);
        if (albumPO == null) {
            return null;
        }
//        2.根据实体对象查找专辑信息并连带查询得出专辑内包含的全部歌曲.多表查询/一对多关系
//        采用Db静态方式，先获取实体对象，再使用Db的lambda静态方法来完成，可以避免Bean的循环依赖

//        获取艺人信息
        ArtistPO artistPO = Db.lambdaQuery(ArtistPO.class)
                .eq(ArtistPO::getCName, albumPO.getACast())
                .one();
        ArtistSearchVO searchVO = BeanUtil.copyProperties(artistPO, ArtistSearchVO.class);

//        获取该专辑下的所有歌曲
        List<SongPO> songPOList = Db.lambdaQuery(SongPO.class)
                .eq(SongPO::getSBelonged, albumPO.getAName())
                .orderBy(true, true, SongPO::getSOrder)
                .list();
//        3.封装VO对象

        AlbumVO vo = BeanUtil.copyProperties(albumPO, AlbumVO.class);
        vo.setSongList(BeanUtil.copyToList(songPOList, SongVO.class));
        vo.setACaster(searchVO);
        return vo;
    }

    @Override
    public PageDTO<AlbumListSearchVO> getAlbumByType(AlbumPageQuery query) {
        String item = query.getType();
        Page<AlbumPO> page = query.toPage(new OrderItem().setAsc(true).setColumn("a_id"));

        switch (item) {
            case "cpop":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "国语流行")
                        .or()
                        .eq(AlbumPO::getAType, "华语流行").page(page);
                break;
            case "kpop":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "K-Pop")
                        .page(page);
                break;
            case "jpop":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "J-Pop")
                        .page(page);
                break;
            case "eupop":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "欧美流行")
                        .page(page);
                break;
            case "folk":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "民谣")
                        .page(page);
                break;
            case "rock":
                page = lambdaQuery()
                        .eq(AlbumPO::getAType, "摇滚")
                        .page(page);
                break;
        }
        return PageDTO.of(page, AlbumListSearchVO.class);
    }
}
