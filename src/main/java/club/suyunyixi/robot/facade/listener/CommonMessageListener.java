package club.suyunyixi.robot.facade.listener;

import club.suyunyixi.robot.application.GroupMessageApplication;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ExceptionHandler;
import club.suyunyixi.robot.infrastructure.utils.ThreadLocalUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.definition.Group;
import love.forte.simbot.definition.Member;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.ReceivedMessageContent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息监听器
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:31
 */
@Slf4j
@Component
public class CommonMessageListener {
    @Resource
    private ThreadLocalUtil<GroupMessageEvent> threadLocalUtil;
    @Resource
    private GroupMessageApplication application;

    /**
     * 好友消息监听
     *
     * @param event {@link FriendMessageEvent}
     */
    @Listener
    @ExceptionHandler
    public void listener(FriendMessageEvent event) {
        event.replyBlocking("Hello World!\nWelcome to Chat-Robot, be enjoyed!");
    }

    /**
     * 群消息监听
     *
     * @param event {@link GroupMessageEvent}
     */
    @Listener
    @ExceptionHandler
    public void listener(GroupMessageEvent event) {
        BaseParam param = explain(event);
        // log
        log.info("listened group message group: {}\n{}", param.getGroupNum(), JSON.toJSONString(param));
        // set threadLocal
        threadLocalUtil.set(event);
        // chain
        application.sendMessage(param);
        // remove
        threadLocalUtil.remove();
    }

    private BaseParam explain(GroupMessageEvent event) {
        BaseParam param = new BaseParam();
        // group info
        Group group = event.getSource();
        // sender info
        Member sender = event.getAuthor();
        // message info
        ReceivedMessageContent content = event.getMessageContent();
        return param.setIfTrue(ObjectUtil.isNotNull(group) && ObjectUtil.isNotNull(group.getId()), () -> param.setGroupNum(group.getId().toString()))
                .setIfTrue(ObjectUtil.isNotNull(sender) && ObjectUtil.isNotNull(sender.getId()), () -> param.setAt(sender.getId().toString()))
                .setIfTrue(ObjectUtil.isNotNull(content) && ObjectUtil.isNotNull(content.getPlainText()), () -> param.setReqMessage(content.getPlainText()))
                .setSource(MessageSource.GROUP)
                ;
    }

}