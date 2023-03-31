package club.suyunyixi.robot.infrastructure.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/3 14:26@
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {
    private static long APPROACHING = 10 * 60L;

    public static boolean approaching(Long time) {
        if (ObjectUtil.isNotNull(time)) {
            return between(System.currentTimeMillis() - time);
        }
        return Boolean.FALSE;
    }

    private static boolean between(long l) {
        return l >= 1_000_000_000_000L ? l <= APPROACHING * 1000 : l <= APPROACHING;
    }
}