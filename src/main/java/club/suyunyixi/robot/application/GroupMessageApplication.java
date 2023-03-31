package club.suyunyixi.robot.application;

import club.suyunyixi.robot.application.chain.MessageFilterChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.base.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ExceptionHandler;
import club.suyunyixi.robot.infrastructure.utils.BotUtil;
import club.suyunyixi.robot.infrastructure.utils.ThreadLocalUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.Messages;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 消息处理
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:13
 **/
@Slf4j
@Component
public class GroupMessageApplication {

    @Resource
    private MessageFilterChain chain;
    @Resource
    private ThreadLocalUtil<GroupMessageEvent> threadLocalUtil;

    /**
     * 发送消息
     *
     * @param param {@link BaseParam}
     */
    @ExceptionHandler
    public void sendMessage(BaseParam param) {
        GroupMessageEvent event = threadLocalUtil.get();
        if (ObjectUtil.isNotNull(event)) {
            // execute chain to choice biz
            BaseRespMessage rep = chain.findMain(MessageSource.GROUP).handle(param, BaseContext.empty());
            // assemble resp message
            Pair<Boolean, Messages> pair = BotUtil.assemble(rep);
            // send
            event.getGroup().sendAsync(pair.getValue());
        } else {
            log.error("event null, why are you choice do this?");
        }
    }
}