package club.suyunyixi.robot.domain.command;

import club.suyunyixi.robot.infrastructure.exception.CanNotParseCommandException;
import club.suyunyixi.robot.infrastructure.exception.InsufficientPermissionsException;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

/**
 * 机器人命令处理接口
 *
 * @author Suyunyixi
 * @description
 * @date 2023/2/6
 */
public interface CommandHandler {
    /**
     * 解析命令
     *
     * @param event 命令事件
     * @return 解析完成后返回的消息
     * @throws {@link CanNotParseCommandException} 无法解析时请抛出此异常
     * @throws {@link InsufficientPermissionsException} 没有足够权限时请抛出此异常
     */
    List<MessageChain> parseCommand(MessageEvent event) throws CanNotParseCommandException, InsufficientPermissionsException;
}