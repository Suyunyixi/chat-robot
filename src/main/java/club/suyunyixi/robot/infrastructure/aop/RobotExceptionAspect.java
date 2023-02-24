package club.suyunyixi.robot.infrastructure.aop;

import club.suyunyixi.robot.infrastructure.exception.*;
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
        } catch (CanNotExplainException e) {
            log.error("爷解析不了这句话!!!");
        } catch (CanNotSendMessageException e) {
            log.error("爷回复失败了!!!");
        } catch (InsufficientPermissionsException e) {
            log.error("你没有权限就别指挥爷了!!!");
        } catch (NeedNotHandleException e) {
            log.error("这个也不处理!!!");
        } catch (BizException e) {
            log.error("处理失败了: {}", e.getMessage());
        } catch (Exception e) {
            log.error("啊? 啷个报错了!!!: {}", e.getMessage());
        } finally {
            // remove
            threadLocalUtil.remove();
        }
        return null;
    }
}