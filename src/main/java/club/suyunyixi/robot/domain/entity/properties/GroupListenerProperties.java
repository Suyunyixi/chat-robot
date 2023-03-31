package club.suyunyixi.robot.domain.entity.properties;

import cn.hutool.core.collection.ListUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/31 15:06
 * @email xukai@co-mall.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "group.task")
public class GroupListenerProperties {
    private List<Config> configs = ListUtil.toList();

    public Config findByEvent(Event event) {
        for (Config config : configs) {
            if (config.sameEvent(event)) {
                return config;
            }
        }
        return null;
    }

    @Data
    public static class Config {
        private Event event;
        private List<String> bots = ListUtil.toList();
        private List<String> groups = ListUtil.toList();
        private String msg;

        public boolean sameEvent(Event event) {
            return this.event == event;
        }
    }

    public enum Event {
        /**
         * 天气
         */
        WEATHER,
        GOOD_MORNING,
        DRINK_MILK_TEA,
        ;
    }
}