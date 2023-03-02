package club.suyunyixi.robot.infrastructure.register;

import club.suyunyixi.robot.domain.command.AbstractHandler;
import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import club.suyunyixi.robot.infrastructure.error.BizError;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.EnumMap;
import java.util.Map;

/**
 * handler策略模式注册
 *
 * @author Suyunyixi
 * @date 2022/9/23 18:37
 **/
@Slf4j
@Component
@Configuration
public class HandlerRegister {
    @Resource
    private ApplicationContext applicationContext;

    private EnumMap<MessageHandler, Object> services;

    /**
     * @param type {@link MessageHandler}
     */
    public AbstractHandler get(MessageHandler type) {
        Object o = services.getOrDefault(type, null);
        if (ObjectUtil.isNotNull(o)) {
            return (AbstractHandler) o;
        }
        // broken
        ExceptionUtil.throwException(BizError.HANDLER_NOT_FOUND);
        return null;
    }

    @PostConstruct
    public void init() {
        // 注册类型对象
        services();
        log.info("已注册handler: {}", services);
    }

    /**
     * 处理所有service
     */
    private void services() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(MessageHandlerStrategy.class);
        if (CollUtil.isEmpty(services)) {
            services = new EnumMap<>(MessageHandler.class);
        }
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object value = entry.getValue();
            // 依次获取到 使用了该注解的对象
            Class<?> aClass = AopUtils.getTargetClass(value);
            // 获取当前对象 声明的注解 获取到注解后 还可以获取注解中的属性
            MessageHandlerStrategy service = aClass.getDeclaredAnnotation(MessageHandlerStrategy.class);
            add(service, value);
        }
    }

    /**
     * 添加到map
     */
    private void add(MessageHandlerStrategy service, Object value) {
        if (ObjectUtil.isNotNull(service)) {
            services.put(service.handler(), value);
        }
    }
}