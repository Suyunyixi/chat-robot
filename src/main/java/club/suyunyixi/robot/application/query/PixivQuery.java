package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.domain.entity.constants.ReqUrlConstant;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 * @date 2023/2/10 18:10
 * @email xukai@co-mall.com
 */
@Slf4j
@Component
public class PixivQuery {

    private String search(String word, Boolean safe) {
        return HttpRequest.get(url(word, ReqUrlConstant.DEFAULT_ORDER, ReqUrlConstant.DEFAULT_MODE, safe))
                .execute()
                .body();
    }

    private String url(String word, String sort, String model, boolean safe) {
        return CharSequenceUtil.format(ReqUrlConstant.PIXIV, word, sort, model, safe ? ReqUrlConstant.DEFAULT_SAFE : ReqUrlConstant.R18);
    }
}