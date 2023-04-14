package club.suyunyixi.robot.facade.task;

import club.suyunyixi.robot.application.query.JuheQuery;
import club.suyunyixi.robot.infrastructure.entity.dto.juhe.JuheWeatherDTO;
import club.suyunyixi.robot.infrastructure.config.properties.GroupListenerProperties;
import club.suyunyixi.robot.infrastructure.utils.ApplicationUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import love.forte.simbot.Identifies;
import love.forte.simbot.component.mirai.bot.MiraiBot;
import love.forte.simbot.definition.Group;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static club.suyunyixi.robot.infrastructure.entity.constants.JuheConstant.DEFAULT_CITY;

/**
 * 定时任务管理
 *
 * @author Suyunyixi
 * @date 2023/3/2 17:59
 */
@Component
public class TimingTask {
    @Resource
    private ApplicationUtil util;
    @Resource
    private GroupListenerProperties properties;
    @Resource
    private JuheQuery juheQuery;

    @SneakyThrows
    @Scheduled(cron = "0 0 8  * * ? ")
    public void goodMorning() {
        GroupListenerProperties.Config config = properties.findByEvent(GroupListenerProperties.Event.GOOD_MORNING);
        send(config);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 0/1  * * ? ")
    public void weather() {
        GroupListenerProperties.Config config = properties.findByEvent(GroupListenerProperties.Event.WEATHER);
        sendWeather(config);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 15  * * ? ")
    public void drinkMilkTea() {
        GroupListenerProperties.Config config = properties.findByEvent(GroupListenerProperties.Event.DRINK_MILK_TEA);
        send(config);
    }

    private void sendWeather(GroupListenerProperties.Config config) {
        if (ObjectUtil.isNull(config)) {
            return;
        }
        // 查询
        JuheWeatherDTO.RealtimeDTO dto = juheQuery.weather(DEFAULT_CITY);
        // 发送消息
        send(config, dto.realtimeMsg(DEFAULT_CITY));
    }

    private void send(GroupListenerProperties.Config config) {
        send(config, config.getMsg());
    }

    private void send(GroupListenerProperties.Config config, String msg) {
        Optional.ofNullable(config).ifPresent(c -> {
            List<String> bots = c.getBots();
            List<String> groups = c.getGroups();
            for (String botId : bots) {
                for (String groupId : groups) {
                    MiraiBot bot = util.findBot(botId);
                    Group group = bot.getGroup(Identifies.ID(groupId));
                    Optional.ofNullable(group).ifPresent(g -> group.sendAsync(msg));
                }
            }
        });
    }
}