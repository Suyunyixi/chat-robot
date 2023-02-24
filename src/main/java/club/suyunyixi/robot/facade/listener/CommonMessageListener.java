package club.suyunyixi.robot.facade.listener;

import club.suyunyixi.robot.application.GroupMessageApplication;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ExceptionHandler;
import club.suyunyixi.robot.infrastructure.utils.ThreadLocalUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.component.mirai.message.SimbotOriginalMiraiMessage;
import love.forte.simbot.definition.Group;
import love.forte.simbot.definition.Member;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.At;
import love.forte.simbot.message.Message;
import love.forte.simbot.message.ReceivedMessageContent;
import net.mamoe.mirai.message.data.MarketFace;
import net.mamoe.mirai.message.data.SingleMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public void listener(GroupMessageEvent event) {
        // 解析
        BaseParam param = explain(event);
        // log
        log.info("listened group message group: {}\n{}", param.getGroupNum(), JSON.toJSONString(param));
        // set threadLocal
        threadLocalUtil.set(event);
        // chain
        application.sendMessage(param);
    }

    private BaseParam explain(GroupMessageEvent event) {
        // group info
        Group group = event.getSource();
        // sender info
        Member sender = event.getAuthor();
        // message info
        ReceivedMessageContent content = event.getMessageContent();
        // base param
        BaseParam param = explainMessage(content, event.getBot());
        return param.setIfTrue(ObjectUtil.isNotNull(group) && ObjectUtil.isNotNull(group.getId()), () -> param.setGroupNum(group.getId().toString()))
                .setIfTrue(ObjectUtil.isNotNull(sender) && ObjectUtil.isNotNull(sender.getId()), () -> param.setAt(sender.getId().toString()))
                .setIfTrue(ObjectUtil.isNotNull(content) && ObjectUtil.isNotNull(content.getPlainText()), () -> param.setReqMessage(content.getPlainText().trim()))
                .setSource(MessageSource.GROUP)
                ;
    }

    @SuppressWarnings("unchecked")
    private BaseParam explainMessage(ReceivedMessageContent content, Bot bot) {
        BaseParam param = new BaseParam();
        // ats
        List<String> ats = ListUtil.toList();
        // 商店表情
        Map<String, Message.Element<?>> marketFaces = MapUtil.newHashMap();
        // 是否@了机器人
        boolean isAtBot = Boolean.FALSE;
        for (Message.Element<?> message : content.getMessages()) {
            if (message instanceof At at) {
                String qq = at.getTarget().toString();
                // 添加at
                ats.add(qq);
                if (qq.equals(bot.getId().toString())) {
                    isAtBot = true;
                }
            } else if (message instanceof SimbotOriginalMiraiMessage originalMiraiMessage) {
                SingleMessage miraiMessage = originalMiraiMessage.getOriginalMiraiMessage();
                if (miraiMessage instanceof MarketFace marketFace) {
                    marketFaces.put(String.valueOf(marketFace.getId()), message);
                }
            }
        }
        return param.setAtMe(isAtBot).setMarketFaces(marketFaces);
    }
}