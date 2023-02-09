package club.suyunyixi.robot.domain.entity.base;

import lombok.Data;
import lombok.experimental.Accessors;
import love.forte.simbot.Identifies;
import love.forte.simbot.message.At;
import love.forte.simbot.message.Messages;
import love.forte.simbot.message.Text;

import java.util.List;
import java.util.Optional;

/**
 * @author Suyunyixi
 * @date 2023/2/7 10:46
 */
@Data
@Accessors(chain = true)
public class BaseRespMessage {
    /**
     * 需要回复的人
     */
    private List<String> ats;
    /**
     * 内容正文
     */
    private String content;
    /**
     * 图片信息
     */
    private List<RepImage> images;
    /**
     * 是否需要回复
     */
    private boolean reply;

    public static BaseRespMessage none() {
        return new BaseRespMessage();
    }

    @Data
    @Accessors(chain = true)
    public static class RepImage {
        private String str;
        private String url;
    }

    public Messages toMessages() {
        // content
        Messages messages = Messages.toMessages(Text.of(content));
        // ats
        Optional.ofNullable(ats).ifPresent(nums -> nums.forEach(at -> messages.plus(new At(Identifies.ID(at)))));
        // TODO IMAGES
        return messages;
    }
}