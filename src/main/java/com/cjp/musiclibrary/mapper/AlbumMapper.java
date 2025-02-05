package com.cjp.musiclibrary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjp.musiclibrary.domain.po.AlbumPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AlbumMapper extends BaseMapper<AlbumPO> {

}