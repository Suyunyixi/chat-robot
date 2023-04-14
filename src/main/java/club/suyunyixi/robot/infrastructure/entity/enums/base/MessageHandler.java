package club.suyunyixi.robot.infrastructure.entity.enums.base;

import club.suyunyixi.robot.infrastructure.entity.constants.MessageLevelConstant;
import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    QUEST(StrUtil.split("问,提问,请问"), MessageLevelConstant.QUEST_LEVEL),
    CHAT_GPT_QUEST(StrUtil.split(""), MessageLevelConstant.QUEST_LEVEL + 1),
    CHAT_GPT_3_QUEST(StrUtil.split(""), MessageLevelConstant.QUEST_LEVEL),
    MYSTERIOUS_CODE(StrUtil.split("神秘代码,代码"), MessageLevelConstant.MYSTERIOUS_CODE_LEVEL),
    PICTURE_2(StrUtil.split("二刺螈图片,二刺螈,二次元图片"), MessageLevelConstant.PICTURE_2_LEVEL),
    PICTURE_3(StrUtil.split("三次元,三次元图片"), MessageLevelConstant.PICTURE_3_LEVEL),
    PIXIV(StrUtil.split("p,pixiv"), MessageLevelConstant.PICTURE_3_LEVEL),
    PIXIV_R18(StrUtil.split("p18,pixiv18,涩图,高清涩图"), MessageLevelConstant.PICTURE_3_LEVEL),
    EXTRA_AGAIN(StrUtil.split("又,还,6,233"), MessageLevelConstant.EXTRA_LEVEL),
    BAIDU_MAP(StrUtil.split("map,地图,地点"), MessageLevelConstant.NORMAL),
    SENTENCES(StrUtil.split("名人名言,专业"), MessageLevelConstant.NORMAL),
    SHORT_VIDEO(StrUtil.split("好看的,短视频"), MessageLevelConstant.NORMAL),
    LOVE_TALK(StrUtil.split("土味,土味情话"), MessageLevelConstant.NORMAL),
    MUSIC(StrUtil.split("音乐"), MessageLevelConstant.NORMAL),
    EXTRA_MARKET_FACE(StrUtil.split("209590"), MessageLevelConstant.EXTRA_LEVEL),
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

    /**
     * 返回一句话中是否是触发该handler关键字的
     *
     * @param message message
     * @return 触发关键字和该对象的二元组
     */
    public Pair<MessageHandler, String> keyword(String message) {
        Optional<Pair<MessageHandler, String>> pair = this.keywords.stream()
                .filter(k -> CharSequenceUtil.startWith(message.toLowerCase(), k.toLowerCase()))
                .map(keyword -> new Pair<>(this, keyword))
                .findFirst();
        return pair.orElse(null);
    }
}