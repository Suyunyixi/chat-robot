package club.suyunyixi.robot.infrastructure.command.job;

import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import love.forte.simbot.bot.Bot;

/**
 * @author Suyunyixi
 * @date 2023/3/2 17:51
 */
public abstract class FriendJobHandler<T, R extends BaseRespMessage>
        extends AbstractJobHandler<T, R>
        implements Runnable {


    @Override
    public void run() {
        this.execute();
    }


    /**
     * 好友统一回复
     *
     * @param r   R extends BaseRespMessage
     * @param bot {@link Bot}
     */
    @Override
    public void reply(R r, Bot bot) {

    }
}