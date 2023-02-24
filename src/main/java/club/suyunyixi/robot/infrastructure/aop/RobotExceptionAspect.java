package club.suyunyixi.robot.infrastructure.aop;

import club.suyunyixi.robot.infrastructure.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.event.GroupMessageEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Suyunyixi
 * @date 2023/2/15 21:38
 **/
@Slf4j
@Aspect
@Component
public class RobotExceptionAspect {

    @Resource
    private ThreadLocalUtil<GroupMessageEvent> threadLocalUtil;

    @Around("@annotation(club.suyunyixi.robot.infrastructure.anno.ExceptionHandler)")
    public Object exceptionHandler(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Exception e) {
            log.error("处理失败了: {}", e.getMessage());
            return null;
        }
    }
}