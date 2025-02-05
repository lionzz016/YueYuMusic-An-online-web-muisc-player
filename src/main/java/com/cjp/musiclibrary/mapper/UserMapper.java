package com.cjp.musiclibrary.mapper;

import com.cjp.musiclibrary.domain.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cjp
 * @since 2025-01-23
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}
