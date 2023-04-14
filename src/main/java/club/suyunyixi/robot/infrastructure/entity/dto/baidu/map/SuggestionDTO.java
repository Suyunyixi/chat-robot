package club.suyunyixi.robot.infrastructure.entity.dto.baidu.map;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/10 11:45
 */
@NoArgsConstructor
@Data
public class SuggestionDTO {

    @JsonAlias("name")
    private String name;
    @JsonAlias("location")
    private LocationDTO location;
    @JsonAlias("uid")
    private String uid;
    @JsonAlias("province")
    private String province;
    @JsonAlias("city")
    private String city;
    @JsonAlias("district")
    private String district;
    @JsonAlias("business")
    private String business;
    @JsonAlias("cityid")
    private String cityid;
    @JsonAlias("tag")
    private String tag;
    @JsonAlias("address")
    private String address;
    @JsonAlias("children")
    private List<?> children;
    @JsonAlias("adcode")
    private String adcode;

    @NoArgsConstructor
    @Data
    public static class LocationDTO {
        @JsonAlias("lat")
        private Double lat;
        @JsonAlias("lng")
        private Double lng;
    }
}