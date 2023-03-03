package club.suyunyixi.robot.infrastructure.utils;

import club.suyunyixi.robot.application.job.BiliBiliFollowGroupJob;
import club.suyunyixi.robot.domain.entity.enums.bili.BiliApiType;
import club.suyunyixi.robot.domain.entity.properties.JobConfigProperties;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import love.forte.simbot.Identifies;
import love.forte.simbot.application.Application;
import love.forte.simbot.application.BotManagers;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.component.mirai.bot.MiraiBot;
import love.forte.simbot.component.mirai.bot.MiraiBotManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Suyunyixi
 * @date 2023/3/3 15:23
 */
@Component
public class ApplicationUtil {
    @Resource
    private Application application;
    @Resource
    private JobConfigProperties jobConfigProperties;

    @SuppressWarnings("unchecked")
    public List<? extends Runnable> tasks() {
        return merge(
                biliTasks()
        );
    }

    @SuppressWarnings("unchecked")
    private List<? extends Runnable> merge(List<? extends Runnable>... tasks) {
        return Stream.of(tasks).flatMap(Collection::stream).toList();
    }

    private List<BiliBiliFollowGroupJob> biliTasks() {
        List<JobConfigProperties.BiliConfig> configs = jobConfigProperties.getBili();
        List<BiliBiliFollowGroupJob> list = ListUtil.toList();
        if (CollUtil.isNotEmpty(configs)) {
            for (JobConfigProperties.BiliConfig config : configs) {
                List<String> groups = config.getGroups();
                List<String> uids = config.getUids();
                List<String> bots = config.getBots();
                BiliApiType type = BiliApiType.getByCode(config.getType());
                if (CollUtil.isNotEmpty(groups)
                        && CollUtil.isNotEmpty(uids)
                        && CollUtil.isNotEmpty(bots)
                        && ObjectUtil.isNotEmpty(type)) {
                    for (String uid : uids) {
                        for (String bot : bots) {
                            MiraiBot miraiBot = findBot(bot);
                            Optional.ofNullable(miraiBot).ifPresent(m -> list.add(new BiliBiliFollowGroupJob(miraiBot, uid, groups, type)));
                        }
                    }
                }
            }
        }
        return list;
    }

    public MiraiBot findBot(String botId) {
        BotManagers botManagers = application.getBotManagers();
        for (BotManager<?> manager : botManagers) {
            // 寻找你所需要的botManager，例如MiraiBotManager
            if (manager instanceof MiraiBotManager miraiBotManager) {
                MiraiBot bot = miraiBotManager.get(Identifies.ID(botId));
                if (ObjectUtil.isNotNull(bot)) {
                    return bot;
                }
            }
        }
        return null;
    }

    public List<MiraiBot> findBots(List<String> botIds) {
        return Optional.ofNullable(botIds).orElse(ListUtil.toList())
                .stream()
                .map(this::findBot)
                .filter(ObjectUtil::isNotNull)
                .toList();
    }
}