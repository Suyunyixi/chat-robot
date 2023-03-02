package club.suyunyixi.robot.domain.command.handler;

import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * 命令处理器管理者, 此处为执行命令的模板
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public interface CommandHandlerManager {
    /**
     * 当命名事件执行成功时需要做的事
     *
     * @param event
     */
    void success(BaseRespMessage rep);

    /**
     * 当命名事件执行失败时需要做的事
     *
     * @param context
     */
    BaseRespMessage fail(BaseContext context);
}