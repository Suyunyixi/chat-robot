package club.suyunyixi.robot.domain.client;

import club.suyunyixi.robot.domain.entity.dto.uomg.UomgDTO;
import club.suyunyixi.robot.domain.entity.dto.uomg.UomgMusicDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Suyunyixi
 * @date 2023/3/13 10:51
 * @email xukai@co-mall.com
 */
@FeignClient(name = "uomg", url = "https://api.uomg.com/api")
public interface UomgClient {

    /**
     * @param format 选择输出格式[json|text|js]
     * @return 土味情话
     */
    @GetMapping(value = "/rand.qinghua", produces = "application/json;charset=utf-8")
    UomgDTO<String> loveTalk(@RequestParam(value = "format") String format);


    /**
     * 网易云音乐，随机歌曲输出
     *
     * @param sort   选择输出分类[热歌榜|新歌榜|飙升榜|抖音榜|电音榜]，为空输出热歌榜
     * @param mid    网易云歌单ID
     * @param format 选择输出格式[json|mp3]
     * @return {@link UomgMusicDTO}
     */
    @GetMapping(value = "/rand.music", produces = "application/json;charset=utf-8")
    UomgDTO<UomgMusicDTO> music(
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "mid") Integer mid,
            @RequestParam(value = "format") String format
    );
}