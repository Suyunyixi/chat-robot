package club.suyunyixi.robot.domain.command.job;

import club.suyunyixi.robot.domain.entity.job.resp.JobGroupRespMessage;
import club.suyunyixi.robot.infrastructure.utils.BotUtil;
import cn.hutool.core.lang.Pair;
import love.forte.simbot.Identifies;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.definition.Group;
import love.forte.simbot.message.Messages;

import java.util.List;

/**
 * 抽象的群组定时任务处理器
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:41
 */
public abstract class GroupJobHandler<T, R extends JobGroupRespMessage>
        extends AbstractJobHandler<T, R>
        implements Runnable, GroupReplyHandler {

    private List<String> groups;

    @Override
    public void run() {
        this.execute();
    }

    @Override
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    /**
     * 群组统一回复
     *
     * @param r   R extends BaseRespMessage
     * @param bot {@link Bot}
     */
    @Override
    public void reply(R r, Bot bot) {
        for (String groupId : groups) {
            Group group = bot.getGroup(Identifies.ID(groupId));
            if (group != null) {
                Pair<Boolean, Messages> pair = BotUtil.assemble(r);
                group.sendAsync(pair.getValue());
            }
        }
    }
}