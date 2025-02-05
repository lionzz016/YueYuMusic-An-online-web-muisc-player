package com.cjp.musiclibrary.service.user;

import com.cjp.musiclibrary.domain.dto.UserExitFormDTO;
import com.cjp.musiclibrary.domain.dto.UserLoginFormDTO;
import com.cjp.musiclibrary.domain.dto.UserRegistryFormDTO;
import com.cjp.musiclibrary.domain.po.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjp.musiclibrary.domain.vo.UserExitVO;
import com.cjp.musiclibrary.domain.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjp
 * @since 2025-01-23
 */
public interface IUserService extends IService<UserPO> {

   UserVO registryUser(UserRegistryFormDTO userRegistryFormDTO);

   UserVO loginUser(UserLoginFormDTO userLoginFormDTO);

    UserExitVO loginOutUser(UserExitFormDTO userExitFormDTO);
}
