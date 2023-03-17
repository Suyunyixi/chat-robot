package club.suyunyixi.robot.domain.client;

import club.suyunyixi.violin.api.apiOpen.dto.APPageDTO;
import club.suyunyixi.violin.api.apiOpen.dto.APSentencesDTO;
import club.suyunyixi.violin.api.apiOpen.dto.APVideoDTO;
import club.suyunyixi.violin.api.apiOpen.dto.ApiOpenDTO;
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
    ApiOpenDTO<APPageDTO<APVideoDTO>> videoPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    );

    /**
     * 名人名言
     */
    @GetMapping(value = "/sentences", produces = "application/json;charset=utf-8")
    ApiOpenDTO<APSentencesDTO> sentences();
}