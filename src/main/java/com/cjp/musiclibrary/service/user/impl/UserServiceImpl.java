package com.cjp.musiclibrary.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.cjp.musiclibrary.domain.dto.UserExitFormDTO;
import com.cjp.musiclibrary.domain.dto.UserLoginFormDTO;
import com.cjp.musiclibrary.domain.dto.UserRegistryFormDTO;
import com.cjp.musiclibrary.domain.po.UserPO;
import com.cjp.musiclibrary.domain.vo.UserExitVO;
import com.cjp.musiclibrary.domain.vo.UserVO;
import com.cjp.musiclibrary.enums.UserEnum;
import com.cjp.musiclibrary.exception.CustomException;
import com.cjp.musiclibrary.mapper.UserMapper;
import com.cjp.musiclibrary.service.user.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjp.musiclibrary.thread.ThreadContext;
import com.cjp.musiclibrary.utils.CodeUtils;
import com.cjp.musiclibrary.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cjp
 * @since 2025-01-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {
    @Override
    public UserVO registryUser(UserRegistryFormDTO userRegistryFormDTO) {
        String name = userRegistryFormDTO.getUName();
        String code = userRegistryFormDTO.getUCode();

        UserPO userPO = lambdaQuery()
                .eq(UserPO::getUName, name)
                .one();
//        如果不为空，说明用户已存在，无法注册
        if (userPO != null)
            throw new CustomException("402", "用户已注册");
//            return null;
//        如果不为空，创建新用户
        UserPO po = new UserPO();
//        判断邀请码
        if (StrUtil.isNotBlank(code)) {
            if (CodeUtils.explain(code)) {
//                设置状态，默认为NORMAL
                po.setUStatus(UserEnum.VIP);
            }
        }

        po.setUName(userRegistryFormDTO.getUName());
        po.setUPassword(DigestUtils.md5DigestAsHex(userRegistryFormDTO.getUPassword().getBytes()));
        po.setUEmail(userRegistryFormDTO.getUEmail());
        if (!save(po)) {
            throw new CustomException("504", "Unexpected error");
//            return null;
        }
        UserPO savedUser = lambdaQuery()
                .eq(UserPO::getUName, name)
                .one();
        return BeanUtil.copyProperties(savedUser, UserVO.class);

    }

    @Override
    public UserVO loginUser(UserLoginFormDTO userLoginFormDTO) {
        String name = userLoginFormDTO.getUName();
        UserPO userPO = lambdaQuery()
                .eq(UserPO::getUName, name)
                .one();

        if (userPO == null)
            throw new CustomException("403", "用户不存在");
//            return null;

        if (DigestUtils.md5DigestAsHex(userLoginFormDTO.getUPassword().getBytes()).equals(userPO.getUPassword())) {
            String token = JwtUtil.generate(userPO.getUId());
            lambdaUpdate()
                    .eq(UserPO::getUId, userPO.getUId())
                    .set(UserPO::getULatesttime, LocalDateTime.now())
                    .update();

            UserVO vo = BeanUtil.copyProperties(userPO, UserVO.class);
            vo.setAuthorization(token);
            return vo;
        } else {
            throw new CustomException("401", "账号或密码错误");
//            return null;
        }
    }

    @Override
    public UserExitVO loginOutUser(UserExitFormDTO userExitFormDTO) {
        Long id = userExitFormDTO.getUId();

        UserPO po = lambdaQuery()
                .eq(UserPO::getUId, id)
                .one();
        UserExitVO vo = BeanUtil.copyProperties(po, UserExitVO.class);
        vo.setUMsg("用户 " + vo.getUName() + " 退出成功");

        lambdaUpdate()
                .eq(UserPO::getUId, id)
                .set(UserPO::getULatesttime, LocalDateTime.now())
                .update();
        return vo;
    }
}
