package club.suyunyixi.robot.infrastructure.client;

import club.suyunyixi.robot.infrastructure.entity.dto.juhe.BaseJuheDTO;
import club.suyunyixi.robot.infrastructure.entity.dto.juhe.JuheWeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:07
 * @email xukai@co-mall.com
 */
@FeignClient(name = "juhe.api", url = "http://apis.juhe.cn")
public interface JuheClient {

    @GetMapping(value = "/simpleWeather/query", produces = "application/x-www-form-urlencoded")
    BaseJuheDTO<JuheWeatherDTO> weather(@RequestParam("city") String city,
                                        @RequestParam("key") String key
    );
}