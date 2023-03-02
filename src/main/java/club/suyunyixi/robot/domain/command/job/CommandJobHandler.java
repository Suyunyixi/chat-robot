package club.suyunyixi.robot.domain.command.job;

import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import love.forte.simbot.bot.Bot;

/**
 * 定时任务的handler
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:07
 */
public interface CommandJobHandler<T, R extends BaseRespMessage> {
    /**
     * 获取回复消息
     *
     * @param message 泛型的响应消息
     * @return 泛型消息转换成回复
     */
    R toMessage(T message);

    /**
     * 执行业务监听操作
     *
     * @return 是否回复的对象
     */
    T listened();

    /**
     * 构建Bot
     *
     * @return {@link Bot}
     */
    Bot bot();

    /**
     * 回复
     *
     * @param r   R extends BaseRespMessage
     * @param bot {@link Bot}
     */
    void reply(R r, Bot bot);
}