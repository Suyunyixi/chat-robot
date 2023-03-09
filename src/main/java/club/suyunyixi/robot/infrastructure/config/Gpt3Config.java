package club.suyunyixi.robot.infrastructure.config;

import club.suyunyixi.robot.domain.entity.properties.FilePathProperties;
import cn.hutool.core.codec.Base64;
import com.unfbx.chatgpt.OpenAiClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author Suyunyixi
 * @date 2023/3/2 15:16
 */
@Slf4j
@Configuration
public class Gpt3Config {

    @Resource
    private FilePathProperties properties;

    @Bean
    public OpenAiClient openAiClient() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7890));
        return OpenAiClient.builder()
                .apiKey(Base64.decodeStr(properties.getGpt3ApiKey()))
                .connectTimeout(120)
                .writeTimeout(120)
                .readTimeout(120)
                .proxy(proxy)
                .build();
    }
}