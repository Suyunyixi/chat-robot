package club.suyunyixi.robot.infrastructure.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/3 15:37@
 */
@Data
@Component
@ConfigurationProperties(prefix = "job.config")
public class JobConfigProperties {
    /**
     * bilibili配置
     */
    private List<BiliConfig> bili;

    @Data
    public static class BiliConfig {
        private List<String> bots;
        private List<String> groups;
        private List<String> uids;
        private Integer type;
    }
}