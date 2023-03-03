package club.suyunyixi.robot.facade.task;

import lombok.SneakyThrows;
import love.forte.simbot.Identifies;
import love.forte.simbot.application.Application;
import love.forte.simbot.application.BotManagers;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.component.mirai.bot.MiraiBot;
import love.forte.simbot.component.mirai.bot.MiraiBotManager;
import love.forte.simbot.definition.Group;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.Optional;

/**
 * @author Suyunyixi
 * @date 2023/3/2 17:59
 */
@Component
public class TimingTask {
    @Resource
    private Application application;

    @SneakyThrows
    @Scheduled(cron = "0 0 8  * * ? ")
    public void goodMorning() {
        BotManagers botManagers = application.getBotManagers();
        // 下面是当存在多个botManager、多个bot的情况时
        for (BotManager<?> manager : botManagers) {
            // 寻找你所需要的botManager，例如MiraiBotManager
            if (manager instanceof MiraiBotManager miraiBotManager) {
                MiraiBot bot = miraiBotManager.get(Identifies.ID("1730814323"));
                Optional.ofNullable(bot).ifPresent(b -> {
                    //  TODO to config
                    Group group = b.getGroup(Identifies.ID("793900042"));
                    Optional.ofNullable(group).ifPresent(g -> group.sendAsync("8点了, 我看还有那头猪还在睡觉!!!"));
                });
            }
        }
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 15  * * ? ")
    public void drinkMiltTea() {
        BotManagers botManagers = application.getBotManagers();
        // 下面是当存在多个botManager、多个bot的情况时
        for (BotManager<?> manager : botManagers) {
            // 寻找你所需要的botManager，例如MiraiBotManager
            if (manager instanceof MiraiBotManager miraiBotManager) {
                MiraiBot bot = miraiBotManager.get(Identifies.ID("1730814323"));
                Optional.ofNullable(bot).ifPresent(b -> {
                    //  TODO to config
                    Group group = b.getGroup(Identifies.ID("793900042"));
                    Optional.ofNullable(group).ifPresent(g -> group.sendAsync("15点了, 饮茶先啦, 做么斯做!!!"));
                });
            }
        }
    }
}