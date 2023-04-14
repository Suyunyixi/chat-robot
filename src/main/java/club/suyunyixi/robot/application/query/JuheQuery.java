package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.infrastructure.client.JuheClient;
import club.suyunyixi.robot.infrastructure.entity.dto.juhe.JuheWeatherDTO;
import club.suyunyixi.robot.infrastructure.config.properties.KeyProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:55
 * @email xukai@co-mall.com
 */
@Component
public class JuheQuery {
    @Resource
    private JuheClient client;
    @Resource
    private KeyProperties properties;

    public JuheWeatherDTO.RealtimeDTO weather(String city) {
        return client.weather(city, properties.getWeather()).getResult().getRealtime();
    }
}