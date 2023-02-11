package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.domain.entity.constants.ReqUrlConstant;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.XML;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/2/10 18:10
 */
@Slf4j
@Component
public class PixivQuery {

    public String search(String word, Boolean safe) {
        String body = HttpRequest.get(url(word, ReqUrlConstant.DEFAULT_ORDER, ReqUrlConstant.DEFAULT_MODE, safe))
                .execute()
                .body();
        log.info("pixiv body: {}", body);
        return XML.toJSONObject(body).toString();
    }

    private String url(String word, String sort, String model, boolean safe) {
        String url = CharSequenceUtil.format(ReqUrlConstant.PIXIV, word, sort, model, safe ? ReqUrlConstant.DEFAULT_SAFE : ReqUrlConstant.R18);
        log.info("pixiv url: {}", url);
        return url;
    }
}