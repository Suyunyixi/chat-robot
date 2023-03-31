package club.suyunyixi.robot.infrastructure.anno;

import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;

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
    MessageHandler handler();
}