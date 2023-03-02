package club.suyunyixi.robot.domain.command.job;

import club.suyunyixi.robot.domain.entity.job.JobGroupRespMessage;
import club.suyunyixi.robot.infrastructure.utils.BotUtil;
import cn.hutool.core.lang.Pair;
import love.forte.simbot.Identifies;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.definition.Group;
import love.forte.simbot.message.Messages;

/**
 * 抽象的群组定时任务处理器
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:41
 */
public abstract class GroupJobHandler<T, R extends JobGroupRespMessage>
        extends AbstractJobHandler<T, R>
        implements Runnable {

    @Override
    public void run() {
        this.execute();
    }


    /**
     * 群组统一回复
     *
     * @param r   R extends BaseRespMessage
     * @param bot {@link Bot}
     */
    @Override
    public void reply(R r, Bot bot) {
        for (String groupId : r.getGroups()) {
            Group group = bot.getGroup(Identifies.ID(groupId));
            if (group != null) {
                Pair<Boolean, Messages> pair = BotUtil.assemble(r);
                group.sendAsync(pair.getValue());
            }
        }
    }
}