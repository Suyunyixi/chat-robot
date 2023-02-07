package club.suyunyixi.robot.facade.controller;

import club.suyunyixi.robot.facade.param.UserLoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suyunyixi
 * @date 2023/2/6 22:23
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public void login(@RequestBody UserLoginParam param) {

        // TODO login 尝试非配置文件登录
    }
}