package club.suyunyixi.robot.domain.entity.enums;

import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static club.suyunyixi.robot.domain.entity.constants.MessageLevelConstant.*;

/**
 * @author Suyunyixi
 * @date 2023/2/9 10:19
 */
@Getter
@AllArgsConstructor
public enum MessageHandler {
    /**
     * 普通提问, 优先级不高
     */
    QUEST(StrUtil.split("问,提问,请问"), QUEST_LEVEL),
    MYSTERIOUS_CODE(StrUtil.split("神秘代码,代码"), MYSTERIOUS_CODE_LEVEL),
    PICTURE_2(StrUtil.split("二刺螈图片,二刺螈,二次元图片"), PICTURE_2_LEVEL),
    PICTURE_3(StrUtil.split("三次元,三次元图片"), PICTURE_3_LEVEL),
    ;

    /**
     * 权限关键字
     */
    private final Set<String> keywords;
    /**
     * 权重
     */
    private final int level;
}