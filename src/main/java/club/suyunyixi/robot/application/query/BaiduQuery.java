package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.domain.client.BaiduMapClient;
import club.suyunyixi.robot.domain.entity.dto.baidu.map.BaseMapDTO;
import club.suyunyixi.robot.domain.entity.dto.baidu.map.SuggestionDTO;
import club.suyunyixi.robot.domain.entity.properties.KeyProperties;
import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static club.suyunyixi.robot.domain.entity.constants.BaiduConstant.DEFAULT_CITY;

/**
 * @author Suyunyixi
 * @date 2023/3/10 11:39
 */
@Component
public class BaiduQuery {
    @Resource
    private BaiduMapClient mapClient;
    @Resource
    private KeyProperties keyProperties;

    private final String baiduAk = Base64.decodeStr(keyProperties.getBaiduAk());

    /**
     * 百度地图 -> 地点检索 -> 默认城市: 武汉市检索
     *
     * @param keyword 地点关键字
     */
    public List<BaseMapDTO<SuggestionDTO>> defaultMapSearch(String keyword) {
        return suggestion(keyword, DEFAULT_CITY);
    }

    /**
     * 百度地图 -> 地点检索
     *
     * @param keyword 搜索关键词
     * @param city    城市
     */
    public List<BaseMapDTO<SuggestionDTO>> suggestion(String keyword, String city) {
        String json = mapClient.suggestion(keyword,
                city,
                baiduAk,
                Boolean.TRUE,
                "json");
        return JSON.parseObject(json, new TypeReference<List<BaseMapDTO<SuggestionDTO>>>() {
        });
    }
}