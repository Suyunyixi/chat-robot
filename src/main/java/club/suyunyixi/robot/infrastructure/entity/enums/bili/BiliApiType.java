package club.suyunyixi.robot.infrastructure.entity.enums.bili;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Suyunyixi
 * @date 2023/3/3 14:02@
 */
@Getter
@AllArgsConstructor
public enum BiliApiType {
    SPACE(0),
    VIDEO(1),
    ;

    private int code;

    public static BiliApiType getByCode(Integer code) {
        if (ObjectUtil.isNotNull(code)) {
            for (BiliApiType value : values()) {
                if (value.code == code) {
                    return value;
                }
            }
        }
        return null;
    }
}