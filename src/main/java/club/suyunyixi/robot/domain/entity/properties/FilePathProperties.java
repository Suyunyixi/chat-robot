package club.suyunyixi.robot.domain.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/3/2 14:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "file.path")
public class FilePathProperties {
    private String gpt3ApiKey;
}