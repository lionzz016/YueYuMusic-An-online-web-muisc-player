package com.cjp.musiclibrary.controller;


import com.cjp.musiclibrary.comment.Result;
import com.cjp.musiclibrary.domain.dto.UserExitFormDTO;
import com.cjp.musiclibrary.domain.dto.UserLoginFormDTO;
import com.cjp.musiclibrary.domain.dto.UserRegistryFormDTO;
import com.cjp.musiclibrary.domain.vo.UserExitVO;
import com.cjp.musiclibrary.domain.vo.UserVO;
import com.cjp.musiclibrary.service.user.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cjp
 * @since 2025-01-23
 */
@RestController
@RequestMapping("/yueyumusic/user")
@Tag(name = "用户相关接口")
@CrossOrigin
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("registry")
    public Result<UserVO> register(@RequestBody @Validated UserRegistryFormDTO userRegistryFormDTO) {
        return Result.success(userService.registryUser(userRegistryFormDTO));
    }


    @PostMapping("login")
    public Result<UserVO> login(@RequestBody @Validated UserLoginFormDTO userLoginFormDTO) {
        return Result.success(userService.loginUser(userLoginFormDTO));
    }

    @PostMapping("exit")
    public Result<UserExitVO> loginOut(@RequestBody UserExitFormDTO userExitFormDTO) {
        return Result.success(userService.loginOutUser(userExitFormDTO));
    }
}
