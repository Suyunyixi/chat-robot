package club.suyunyixi.robot.infrastructure.anno;

import java.lang.annotation.*;

/**
 * 异常拦截处理
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:59
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionHandler {
}