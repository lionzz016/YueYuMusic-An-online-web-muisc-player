package com.cjp.musiclibrary.mapper;

import com.cjp.musiclibrary.domain.po.ArtistPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cjp
 * @since 2025-01-21
 */
@Repository
@Mapper
public interface ArtistMapper extends BaseMapper<ArtistPO> {

}
