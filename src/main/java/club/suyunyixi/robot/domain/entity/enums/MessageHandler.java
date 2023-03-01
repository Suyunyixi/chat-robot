package club.suyunyixi.robot.domain.entity.enums;

import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
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
    CHAT_GPT_QUEST(StrUtil.split(""), QUEST_LEVEL),
    MYSTERIOUS_CODE(StrUtil.split("神秘代码,代码"), MYSTERIOUS_CODE_LEVEL),
    PICTURE_2(StrUtil.split("二刺螈图片,二刺螈,二次元图片"), PICTURE_2_LEVEL),
    PICTURE_3(StrUtil.split("三次元,三次元图片"), PICTURE_3_LEVEL),
    PIXIV(StrUtil.split("p,pixiv"), PICTURE_3_LEVEL),
    PIXIV_R18(StrUtil.split("p18,pixiv18,涩图,高清涩图"), PICTURE_3_LEVEL),
    EXTRA_AGAIN(StrUtil.split("又,6,233"), EXTRA_LEVEL),
    EXTRA_MARKET_FACE(StrUtil.split("209590"), EXTRA_LEVEL),
    ;

    /**
     * 权限关键字
     */
    private final Set<String> keywords;
    /**
     * 权重
     */
    private final int level;

    private static final Set<MessageHandler> EXTRA = new HashSet<>();

    static {
        EXTRA.add(EXTRA_AGAIN);
        EXTRA.add(EXTRA_MARKET_FACE);
    }

    public static boolean isExtra(MessageHandler handler) {
        return EXTRA.contains(handler);
    }
}