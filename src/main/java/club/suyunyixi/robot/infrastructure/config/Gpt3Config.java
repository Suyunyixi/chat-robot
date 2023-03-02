package club.suyunyixi.robot.infrastructure.config;

import club.suyunyixi.robot.domain.entity.properties.FilePathProperties;
import cn.hutool.core.io.file.FileReader;
import com.unfbx.chatgpt.OpenAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Suyunyixi
 * @date 2023/3/2 15:16
 */
@Configuration
public class Gpt3Config {

    @Resource
    private FilePathProperties properties;

    @Bean
    public OpenAiClient openAiClient() {
        return new OpenAiClient(new FileReader(properties.getGpt3ApiKey()).readString(), 60, 60, 60);
    }
}