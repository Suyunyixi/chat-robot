package club.suyunyixi.robot.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Suyunyixi
 * @date 2023/3/10 11:23
 */
@FeignClient(name = "baidu.map", url = "https://api.map.baidu.com/place/v2")
public interface BaiduMapClient {

    @GetMapping("/suggestion")
    String suggestion(@RequestParam("query") String keyword,
                                               @RequestParam("region") String region,
                                               @RequestParam("ak") String ak,
                                               @RequestParam(value = "city_limit", defaultValue = "true") boolean cityLimit,
                                               @RequestParam(value = "output", defaultValue = "json") String output
    );
}