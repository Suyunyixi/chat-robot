package club.suyunyixi.robot.domain.entity.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/2/10 17:08
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqUrlConstant {
    public static final String PIXIV = "/pixiv/search/{}/{}/{}?safe={}";
    public static final String DEFAULT_ORDER = "popular";
    public static final String DEFAULT_MODE = "2";
    public static final String DEFAULT_SAFE = "";
    public static final String R18 = "R18";
}