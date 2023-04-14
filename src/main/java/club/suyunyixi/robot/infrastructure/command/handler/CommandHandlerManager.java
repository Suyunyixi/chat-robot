package club.suyunyixi.robot.infrastructure.command.handler;

import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;

/**
 * 命令处理器管理者, 此处为执行命令的模板
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public interface CommandHandlerManager {
    /**
     * 当命名事件执行成功时需要做的事
     */
    void success(BaseRespMessage rep);

    /**
     * 当命名事件执行失败时需要做的事
     *
     * @param context
     */
    BaseRespMessage fail(BaseContext context);
}