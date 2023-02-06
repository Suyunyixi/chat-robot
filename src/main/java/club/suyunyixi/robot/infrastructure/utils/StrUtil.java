package club.suyunyixi.robot.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Suyunyixi
 * @date 2023/2/6 21:58
 * @email xukai@co-mall.com
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrUtil {
    public static String getErrorInfoFromException(Throwable e) {
        try {
            try (
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
            ) {
                e.printStackTrace(pw);
                return sw.toString();
            }
        } catch (Exception e1) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}