package club.suyunyixi.robot.facade.task;

import club.suyunyixi.robot.infrastructure.utils.ApplicationUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import love.forte.simbot.Identifies;
import love.forte.simbot.component.mirai.bot.MiraiBot;
import love.forte.simbot.definition.Group;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Suyunyixi
 * @date 2023/3/2 17:59
 */
@Component
public class TimingTask {
    @Resource
    private ApplicationUtil util;

    @SneakyThrows
    @Scheduled(cron = "0 0 8  * * ? ")
    public void goodMorning() {
        MiraiBot bot = util.findBot("1730814323");
        //  TODO to config
        Group group = bot.getGroup(Identifies.ID("793900042"));
        Optional.ofNullable(group).ifPresent(g -> group.sendAsync("8点了, 我看还有那头猪还在睡觉!!!"));
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 15  * * ? ")
    public void drinkMiltTea() {
        MiraiBot bot = util.findBot("1730814323");
        Optional.ofNullable(bot).ifPresent(b -> {
            //  TODO to config
            Group group = b.getGroup(Identifies.ID("793900042"));
            Optional.ofNullable(group).ifPresent(g -> group.sendAsync("15点了, 饮茶先啦, 做么斯做!!!"));
        });
    }
}