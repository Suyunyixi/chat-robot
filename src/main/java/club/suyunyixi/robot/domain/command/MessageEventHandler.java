package club.suyunyixi.robot.domain.command;

import club.suyunyixi.robot.domain.entity.base.BaseContext;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

/**
 * 消息事件处理者，这是 HRobot 的核心处理者，HRobot 设计核心就是为了处理消息并回复消息，HRobot 会自动处理 MessageEventHandler，并保证处理者唯一，而其他事件都需要手动监听事件并注册消费逻辑
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:31
 */
public interface MessageEventHandler {
    /**
     * 处理一个新的消息事件，返回要回复的消息，可以是多条，也可以为 null（代表不回复）
     *
     * @param event
     * @param ctx
     */
    List<MessageChain> handleMessageEvent(MessageEvent event, BaseContext ctx);

    /**
     * 是否应该处理事件，子类应该扩展它
     *
     * @param event
     * @return <strong>如果需要处理，则返回 true；如果不需要处理，则返回 false</strong>
     */
    boolean shouldHandle(MessageEvent event, BaseContext ctx);
}
