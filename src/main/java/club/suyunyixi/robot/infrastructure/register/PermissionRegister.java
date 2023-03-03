package club.suyunyixi.robot.infrastructure.register;

import club.suyunyixi.robot.domain.entity.enums.base.PermissionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册权限用户
 *
 * @author Suyunyixi
 * @date 2023/2/7 11:40
 */
@Slf4j
@Configuration
public class PermissionRegister {
    public static Map<PermissionLevel, Set<String>> MEMBERS = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // build members
    }

    /**
     * 刷新权限用户
     */
    public void refresh() {

    }
}