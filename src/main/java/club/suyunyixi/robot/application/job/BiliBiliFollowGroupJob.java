package club.suyunyixi.robot.application.job;

import club.suyunyixi.robot.application.query.BiliBiliQuery;
import club.suyunyixi.robot.domain.command.job.GroupJobHandler;
import club.suyunyixi.robot.domain.entity.dto.bili.BilibiliDynamic;
import club.suyunyixi.robot.domain.entity.enums.bili.BiliApiType;
import club.suyunyixi.robot.domain.entity.job.resp.JobGroupRespMessage;
import club.suyunyixi.robot.infrastructure.utils.TimeUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import love.forte.simbot.bot.Bot;

import java.util.Date;
import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/3 11:19
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BiliBiliFollowGroupJob
        extends GroupJobHandler<BilibiliDynamic, JobGroupRespMessage> {

    private String uid;
    private BiliApiType type;

    public BiliBiliFollowGroupJob(Bot bot,
                                  String uid,
                                  List<String> groups,
                                  BiliApiType type) {
        super.build(bot);
        super.setGroups(groups);
        this.uid = uid;
        this.type = type;
    }

    @Override
    public JobGroupRespMessage toResp(BilibiliDynamic dynamic) {
        return (JobGroupRespMessage) JobGroupRespMessage.reply(replyMsg(dynamic));
    }

    @Override
    @SneakyThrows
    public BilibiliDynamic listened() {
        BilibiliDynamic dynamic = BiliBiliQuery.getLatestDynamic(uid);
        if (ObjectUtil.isNotNull(dynamic) && TimeUtil.approaching(dynamic.getPubTime())) {
            return dynamic;
        }
        return null;
    }

    public String replyMsg(BilibiliDynamic dynamic) {
        return switch (type) {
            case VIDEO -> String.format("您订阅的番剧 %s 更新啦\n", dynamic.getAuthName()) +
                    String.format("更新时间：%s\n", DateUtil.format(new Date(dynamic.getPubTime()), DatePattern.CHINESE_DATE_FORMAT)) +
                    String.format("跳转链接：%s\n", dynamic.getJumpUrl()) +
                    String.format("更新章节：%s\n", dynamic.getDesc()) +
                    String.format("分享描述：%s\n", dynamic.getPubAction());
            case SPACE -> String.format("您订阅的 up 主 %s %s\n", dynamic.getAuthName(), dynamic.getPubAction()) +
                    String.format("更新时间：%s\n", DateUtil.format(new Date(dynamic.getPubTime()), DatePattern.CHINESE_DATE_FORMAT)) +
                    String.format("跳转链接：%s\n", dynamic.getJumpUrl()) +
                    String.format("描述内容：%s\n", dynamic.getDesc());
        };
    }
}