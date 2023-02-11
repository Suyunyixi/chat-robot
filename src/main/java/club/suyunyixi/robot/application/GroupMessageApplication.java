package club.suyunyixi.robot.application;

import club.suyunyixi.robot.application.chain.MessageFilterChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.utils.BotUtil;
import club.suyunyixi.robot.infrastructure.utils.ThreadLocalUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.Messages;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    public void sendMessage(BaseParam param) {
        GroupMessageEvent event = threadLocalUtil.get();
        if (ObjectUtil.isNotNull(event)) {
            // execute chain to choice biz
            BaseRespMessage rep = chain.findMain(MessageSource.GROUP).handle(param, BaseContext.empty());
            // assemble resp message
            Messages messages = BotUtil.assemble(rep);
            // reply
            event.replyAsync(messages);
        } else {
            log.error("event null, why are you choice do this?");
        }
    }
}