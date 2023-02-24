package club.suyunyixi.robot.domain.entity.base;

import club.suyunyixi.robot.domain.entity.dto.ResqImage;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import love.forte.simbot.message.Message;

import java.util.List;
import java.util.Map;

/**
 * @author Suyunyixi
 * @date 2023/2/7 10:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private List<ResqImage> images;
    /**
     * 表情信息
     */
    private Map<String, Message.Element<?>> marketFaces;
    /**
     * 是否需要回复
     */
    private boolean reply;

    public static BaseRespMessage none() {
        return new BaseRespMessage();
    }

    /**
     * 单一命令回复
     * Created by Suyunyixi on 2023/2/11 20:13
     */
    public static BaseRespMessage reply(BaseContext context, String respContent) {
        if (CharSequenceUtil.isBlank(context.getErrMsg())) {
            return reply(respContent)
                    .setAts(context.getRespAts())
                    .setImages(context.getRespImages());
        } else {
            return reply(context.getErrMsg());
        }
    }

    /**
     * 单一命令回复
     * Created by Suyunyixi on 2023/2/11 20:13
     */
    public static BaseRespMessage replyNoAts(BaseContext context, String respContent) {
        if (CharSequenceUtil.isBlank(context.getErrMsg())) {
            return reply(respContent)
                    .setImages(context.getRespImages());
        } else {
            return reply(context.getErrMsg());
        }
    }

    /**
     * 单一命令回复
     * Created by Suyunyixi on 2023/2/11 20:13
     */
    public static BaseRespMessage replyMarketFaceOnly(BaseContext context, Map<String, Message.Element<?>> marketFaces) {
        if (CharSequenceUtil.isBlank(context.getErrMsg())) {
            return reply("")
                    .setMarketFaces(marketFaces)
                    .setImages(context.getRespImages());
        } else {
            return reply(context.getErrMsg());
        }
    }

    public static BaseRespMessage reply(String respContent) {
        return BaseRespMessage
                .builder()
                .reply(Boolean.TRUE)
                .content(respContent)
                .build();
    }
}