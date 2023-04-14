package club.suyunyixi.robot.infrastructure.entity.dto.juhe;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:12
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class JuheWeatherDTO {

    @JsonProperty("city")
    private String city;
    @JsonProperty("realtime")
    private RealtimeDTO realtime;
    @JsonProperty("future")
    private List<FutureDTO> future;


    @NoArgsConstructor
    @Data
    public static class RealtimeDTO {
        @JsonProperty("temperature")
        private String temperature;
        @JsonProperty("humidity")
        private String humidity;
        @JsonProperty("info")
        private String info;
        @JsonProperty("wid")
        private String wid;
        @JsonProperty("direct")
        private String direct;
        @JsonProperty("power")
        private String power;
        @JsonProperty("aqi")
        private String aqi;

        public String realtimeMsg(String city) {
            return CharSequenceUtil.format(
                    """
                            {}, 日期: {}, 当前天气为: {}, 当前温度: {}
                            """,
                    city, DateUtil.now(), info, temperature
            );
        }
    }

    @NoArgsConstructor
    @Data
    public static class FutureDTO {
        @JsonProperty("date")
        private String date;
        @JsonProperty("temperature")
        private String temperature;
        @JsonProperty("weather")
        private String weather;
        @JsonProperty("wid")
        private WidDTO wid;
        @JsonProperty("direct")
        private String direct;

        @NoArgsConstructor
        @Data
        public static class WidDTO {
            @JsonProperty("day")
            private String day;
            @JsonProperty("night")
            private String night;
        }
    }
}