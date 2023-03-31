package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.domain.entity.constants.ReqUrlConstant;
import club.suyunyixi.robot.infrastructure.utils.IOUtil;
import club.suyunyixi.robot.infrastructure.utils.MapGetter;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.XML;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/2/10 18:10
 */
@Slf4j
@Component
public class PixivQuery {

    /**
     * 能用 HTTP 的就用 HTTP，HTTPS 性能比较低，延迟比较大
     */
    public static final String PID_API = "https://api.lolicon.app/setu/v2?size=original&size=small";
    public static final String beautifulImageUrl = "https://tenapi.cn/acg";
    public static final String chickenSoupUrl = "https://api.shadiao.app/chp/";
    public static final String duChickenSoupUrl = "https://api.shadiao.app/du";


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

    /**
     * 根据 tag 获取 p 站图片 url 地址
     */
    @SneakyThrows
    public String getImagePid(List<String> tags) {
        return searchImage(tags, false, false);
    }

    /**
     * 根据 tag 获取 p '涩' 图片 pid
     */
    public String getSeImagePid(List<String> tags, boolean small) throws IOException {
        return searchImage(tags, true, small);
    }

    public String searchImage(List<String> tags, boolean r18, boolean small) throws IOException {
        StringBuilder url = new StringBuilder(PID_API);
        if (r18) {
            url.append("&r18=1");
        }
        if (tags != null) {
            url.append("&");
            for (String tag : tags) {
                url.append("tag=").append(URLEncoder.encode(tag, StandardCharsets.UTF_8)).append("&");
            }
            url = new StringBuilder(url.substring(0, url.length() - 1));
        }
        log.info("pixiv search url = " + url);
        List<MapGetter> map = IOUtil.sendAndGetResponseMapGetter(new URL(url.toString()), "GET", null, null).getMapGetterList("data");
        if (map == null || map.isEmpty()) {
            return null;
        }
        return small ?
                map.get(0).getMapGetter("urls").getString("small") :
                map.get(0).getMapGetter("urls").getString("original");
    }
}