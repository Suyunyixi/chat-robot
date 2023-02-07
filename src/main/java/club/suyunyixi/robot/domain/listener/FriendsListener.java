package club.suyunyixi.robot.domain.listener;

import lombok.extern.slf4j.Slf4j;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

/**
 * 好友监听器
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:31
 */
@Slf4j
@Component
public class FriendsListener {
    /**
     * 好友消息监听
     *
     * @param event {@link FriendMessageEvent}
     */
    @Listener
    public void listener(FriendMessageEvent event) {
        event.replyBlocking("Hello World");
    }

    /**
     * 群消息监听
     *
     * @param event {@link GroupMessageEvent}
     */
    @Listener
    public void listener(GroupMessageEvent event) {
        event.replyBlocking("Hello World");
    }
}