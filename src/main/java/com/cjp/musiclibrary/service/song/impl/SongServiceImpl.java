package com.cjp.musiclibrary.service.song.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.cjp.musiclibrary.domain.dto.PageDTO;
import com.cjp.musiclibrary.domain.po.AlbumPO;
import com.cjp.musiclibrary.domain.po.ArtistPO;
import com.cjp.musiclibrary.domain.po.SongPO;
import com.cjp.musiclibrary.domain.query.SongPageQuery;
import com.cjp.musiclibrary.domain.vo.AlbumSearchVO;
import com.cjp.musiclibrary.domain.vo.ArtistSearchVO;
import com.cjp.musiclibrary.domain.vo.SongListVO;
import com.cjp.musiclibrary.domain.vo.SongSearchVO;
import com.cjp.musiclibrary.mapper.SongMapper;
import com.cjp.musiclibrary.service.song.ISongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, SongPO> implements ISongService {

    @Override
    public PageDTO<SongSearchVO> querySongByName(SongPageQuery songPageQuery) {
        String name = songPageQuery.getName();

//        1.构造分页条件
        Page<SongPO> page = songPageQuery.toPage(new OrderItem().setColumn("s_id"));
//        2.根据分页条件再查询
        Page<SongPO> songPOPage = new Page<>();
        if (StrUtil.isBlankIfStr(name)) {
            songPOPage = lambdaQuery().page(page);
        } else {
            songPOPage = lambdaQuery()
                    .like(true, SongPO::getSName, name)
                    .page(page);
        }

//        3.封装VO
        return PageDTO.of(songPOPage, SongSearchVO.class);
    }

    @Override
    public Boolean addSongs(List<SongPO> list) {
        return saveBatch(list);
    }

    @Override
    public List<SongListVO> listSpecial() {

        return listSong(409, 417);
    }

    @Override
    public List<SongListVO> listNew() {
        return listSong(101, 402, 418, 419, 420, 421, 422, 423, 424, 425);
    }

    @Override
    public List<SongListVO> listRecommend() {
        return listSong(346, 323, 427, 285, 296, 114, 109, 358, 125, 131, 139, 392, 440, 401, 388, 442, 459);
    }


    public SongListVO getSong(Integer sid) {
        SongPO songPO = getById(sid);
//        找出所有歌手
        List<String> artists = Arrays.stream(songPO.getSArtist().split("/")).toList();
        List<ArtistPO> list = Db.lambdaQuery(ArtistPO.class).in(ArtistPO::getCName, artists).list();
        List<ArtistSearchVO> artistList = BeanUtil.copyToList(list, ArtistSearchVO.class);

//        找出专辑
        AlbumPO one = Db.lambdaQuery(AlbumPO.class).eq(AlbumPO::getAName, songPO.getSBelonged()).one();
        AlbumSearchVO album = BeanUtil.copyProperties(one, AlbumSearchVO.class);

        SongListVO vo = BeanUtil.copyProperties(songPO, SongListVO.class);
        vo.setSArtists(artistList);
        vo.setSAlbum(album);
        return vo;
    }

    public List<SongListVO> listSong(Integer... ids) {
        List<SongListVO> list = new ArrayList<>(ids.length);
        for (Integer id : ids) {
            list.add(getSong(id));
        }
        return list;
    }

    public List<SongListVO> listSong(Integer start, Integer end) {
        List<SongListVO> list = new ArrayList<>(end - start + 1);
        for (int i = start; i < end + 1; i++) {
            list.add(getSong(i));
        }
        return list;
    }

}
