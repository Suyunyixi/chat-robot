package club.suyunyixi.robot.infrastructure.utils;

import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.error.BizError;
import club.suyunyixi.robot.infrastructure.exception.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 异常工具类
 *
 * @author Suyunyixi
 * @date 2023/2/7 11:10
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {
    @SneakyThrows
    public static BaseRespMessage throwEx(Exception e) {
        BaseRespMessage rep = new BaseRespMessage();
        if (e instanceof CanNotParseCommandException) {
        }
        if (e instanceof CanNotSendMessageException) {
        }
        if (e instanceof FileUploadException) {
        }
        if (e instanceof InsufficientPermissionsException) {
        }
        return rep;
    }

    @SneakyThrows
    public static void throwException(BizError error) {
        log.error("触发了异常: {}", error);
        throw new BizException(error);
    }

    public static void throwIfTrue(boolean b, BizError msg) {
        if (b) {
            throwException(msg);
        }
    }

    public static void throwIfTrue(boolean b, Exception msg) {
        if (b) {
            throwEx(msg);
        }
    }
}