package club.suyunyixi.robot.domain.entity.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 0 - 99为保留字段<br>
 * 100 - 999 为系统处理<br>
 * 1000 -  为业务处理<br>
 *
 * @author Suyunyixi
 * @date 2023/2/9 10:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageLevelConstant {
    /**
     * 普通提问权重9999999表示不重要的处理
     */
    public static final int QUEST_LEVEL = 9999999;
    public static final int MYSTERIOUS_CODE_LEVEL = 1001;
    public static final int PICTURE_3_LEVEL = 1002;
    public static final int PICTURE_2_LEVEL = 1003;
    public static final int NORMAL = 99999;
    public static final int EXTRA_LEVEL = 100000;
}