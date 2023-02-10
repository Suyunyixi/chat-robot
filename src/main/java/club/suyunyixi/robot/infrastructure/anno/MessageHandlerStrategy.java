package club.suyunyixi.robot.infrastructure.anno;

import java.lang.annotation.*;

/**
 * 策略处理消息
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:03
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageHandlerStrategy {
}