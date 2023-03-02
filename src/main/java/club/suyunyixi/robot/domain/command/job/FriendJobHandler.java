package club.suyunyixi.robot.domain.command.job;

import club.suyunyixi.robot.domain.entity.job.JobFriendRespMessage;
import love.forte.simbot.bot.Bot;

/**
 * @author Suyunyixi
 * @date 2023/3/2 17:51
 * @email xukai@co-mall.com
 */
public abstract class FriendJobHandler<T, R extends JobFriendRespMessage>
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
        for (String friendId : r.getFriends()) {
            // 主动对好友回复
        }
    }
}