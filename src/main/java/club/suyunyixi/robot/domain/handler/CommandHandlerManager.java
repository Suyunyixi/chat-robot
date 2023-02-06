package club.suyunyixi.robot.domain.handler;

import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * 命令处理器管理者
 *
 * @author Suyunyixi
 * @description
 * @date 2023/2/6
 */
public interface CommandHandlerManager {
    /**
     * 当命名事件执行成功时需要做的事
     *
     * @param event
     */
    void success(MessageEvent event);

    /**
     * 当命名事件执行失败时需要做的事
     *
     * @param event
     */
    void fail(MessageEvent event, String errorMsg);

    /**
     * 当命名事件执行失败时需要做的事
     *
     * @param event
     */
    default void fail(MessageEvent event, Throwable e) {
        fail(event, StrUtil.getErrorInfoFromException(e));
    }
}