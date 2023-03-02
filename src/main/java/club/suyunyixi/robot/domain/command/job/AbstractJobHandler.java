package club.suyunyixi.robot.domain.command.job;

import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象的定时任务处理
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:14
 */
@Slf4j
public abstract class AbstractJobHandler<T, R extends BaseRespMessage>
        implements CommandJobHandler<T, R> {

    public void execute() {
        T message = listened();
        if (ObjectUtil.isNotNull(message)) {
            R r = toMessage(message);
            if (r.isReply()) {
                reply(r, bot());
            }
        } else {
            log.trace("{}, 未收到消息", this.getClass().getSimpleName());
        }
    }
}