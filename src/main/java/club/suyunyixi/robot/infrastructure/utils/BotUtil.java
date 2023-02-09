package club.suyunyixi.robot.infrastructure.utils;

import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import cn.hutool.core.util.ObjectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.At;
import love.forte.simbot.message.Message;
import love.forte.simbot.message.Messages;

/**
 * @author Suyunyixi
 * @date 2023/2/9 11:47
 * @email xukai@co-mall.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotUtil {

    /**
     * 是否@Bot
     *
     * @param event {@link GroupMessageEvent}
     */
    @SuppressWarnings("unchecked")
    public static boolean isMe(GroupMessageEvent event) {
        Messages messages = event.getMessageContent().getMessages();
        if (ObjectUtil.isNotNull(messages)) {
            for (Message.Element<?> element : messages.toList()) {
                if (element instanceof At && event.getBot().isMe(((At) element).getTarget())) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public static Messages assemble(BaseRespMessage rep) {

    }
}
