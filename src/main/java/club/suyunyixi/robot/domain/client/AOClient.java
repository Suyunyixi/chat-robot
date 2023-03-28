package club.suyunyixi.robot.domain.client;

import club.suyunyixi.robot.domain.entity.dto.ao.AOPageDTO;
import club.suyunyixi.robot.domain.entity.dto.ao.APSentencesDTO;
import club.suyunyixi.robot.domain.entity.dto.ao.APVideoDTO;
import club.suyunyixi.robot.domain.entity.dto.ao.ApiOpenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Suyunyixi
 * @date 2023/3/13 14:03
 * @email xukai@co-mall.com
 */
@FeignClient(name = "api.open", url = "https://api.apiopen.top/api")
public interface AOClient {
    /**
     * 分页短视频
     */
    @GetMapping(value = "/getHaoKanVideo", produces = "application/json;charset=utf-8")
    ApiOpenDTO<AOPageDTO<APVideoDTO>> videoPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    );

    /**
     * 名人名言
     */
    @GetMapping(value = "/sentences", produces = "application/json;charset=utf-8")
    ApiOpenDTO<APSentencesDTO> sentences();
}