package club.suyunyixi.robot.domain.entity.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/2/7 10:46
 */
@Data
@Accessors(chain = true)
public class BaseRepMessage {
    /**
     * 需要回复的人
     */
    private String at;
    /**
     * 内容正文
     */
    private String content;
    /**
     * 图片信息
     */
    private List<RepImage> images;

    @Data
    @Accessors(chain = true)
    public static class RepImage {
        private String str;
        private String url;
    }
}