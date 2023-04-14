package club.suyunyixi.robot.infrastructure.command.job;

import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.bot.Bot;

/**
 * 抽象的定时任务处理
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:14
 */
@Slf4j
public abstract class AbstractJobHandler<T, R extends BaseRespMessage>
        implements CommandJobHandler<T, R>, RepeatHandler {

    private Bot bot;

    private String flag = "";

    public void build(Bot bot) {
        this.bot = bot;
    }

    @Override
    public Bot bot() {
        return bot;
    }

    @Override
    public boolean isRepeated(String str) {
        String md5 = MD5.create().digestHex16(str);
        return CharSequenceUtil.equals(md5, flag);
    }

    @Override
    public void setRepeat(String str) {
        flag = MD5.create().digestHex16(str);
    }

    public void execute() {
        T message = listened();
        if (ObjectUtil.isNotNull(message)) {
            R r = toResp(message);
            if (r.isReply() && !isRepeated(r.getContent())) {
                reply(r, bot());
                setRepeat(r.getContent());
            }
        } else {
            log.trace("{}, 未收到消息", this.getClass().getSimpleName());
        }
    }
}