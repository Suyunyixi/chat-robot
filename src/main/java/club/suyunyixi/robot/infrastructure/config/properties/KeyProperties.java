package club.suyunyixi.robot.infrastructure.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/3/2 14:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "key")
public class KeyProperties {
    private String gpt3ApiKey;
    private String baiduAk;
    private String weather;
}