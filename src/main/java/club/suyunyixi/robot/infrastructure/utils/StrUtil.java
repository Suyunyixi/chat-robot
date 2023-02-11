package club.suyunyixi.robot.infrastructure.utils;

import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Suyunyixi
 * @date 2023/2/6 21:58
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrUtil {
    public static final String BLANK = "";

    public static String reqContent(String str, MessageHandler handler) {
        for (String keyword : handler.getKeywords()) {
            if (CharSequenceUtil.startWith(str, keyword)) {
                return CharSequenceUtil.replaceFirst(str, keyword, BLANK).trim();
            }
        }
        return BLANK;
    }

    public static MessageHandler getStartWith(String message) {
        return Arrays.stream(MessageHandler.values())
                .filter(m -> m.getKeywords().stream()
                        .anyMatch(k -> CharSequenceUtil.startWith(message.toLowerCase(), k.toLowerCase())))
                .min(Comparator.comparingLong(MessageHandler::getLevel))
                .orElse(null);
    }

    public static Set<String> split(String str) {
        return new HashSet<>(Arrays.asList(Optional.ofNullable(str).orElse("").replace(" ", "").split(",")));
    }

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