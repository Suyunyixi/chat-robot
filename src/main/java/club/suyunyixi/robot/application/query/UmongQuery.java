package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.infrastructure.client.UomgClient;
import club.suyunyixi.robot.infrastructure.entity.dto.uomg.UomgMusicDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:58
 * @email xukai@co-mall.com
 */
@Component
public class UmongQuery {
    @Resource
    private UomgClient client;

    public String loveTalk() {
        return client.loveTalk("json").getData();
    }

    public UomgMusicDTO music() {
        return client.music("新歌榜", null, "json").getData();
    }
}