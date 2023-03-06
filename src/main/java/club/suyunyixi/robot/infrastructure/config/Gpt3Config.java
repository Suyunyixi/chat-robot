package club.suyunyixi.robot.infrastructure.config;

import club.suyunyixi.robot.domain.entity.properties.FilePathProperties;
import cn.hutool.core.io.file.FileReader;
import com.unfbx.chatgpt.OpenAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

import java.net.InetSocketAddress;
import java.net.Proxy;

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
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7890));
        String apiKey = new FileReader(properties.getGpt3ApiKey()).readString();
        return OpenAiClient.builder()
                .apiKey(apiKey)
                .connectTimeout(120)
                .writeTimeout(120)
                .readTimeout(120)
                .proxy(proxy)
                .build();
    }
}