package club.suyunyixi.robot.domain.entity.base;

import club.suyunyixi.robot.domain.entity.dto.base.ResqImage;
import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;
import cn.hutool.core.collection.ListUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用于传递的上下文对象
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseContext {
    private MessageHandler handler;
    private String reqContent;
    /**
     * 需要回复的人
     */
    private List<String> respAts = ListUtil.toList();
    /**
     * 需要传递的图片
     */
    private List<ResqImage> respImages;
    /**
     * error msg
     */
    private String errMsg;

    public boolean isExtra() {
        return MessageHandler.isExtra(handler);
    }

    public static BaseContext empty() {
        return BaseContext.builder().build();
    }
}