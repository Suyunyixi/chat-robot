package club.suyunyixi.robot.infrastructure.exception;

import club.suyunyixi.robot.infrastructure.exception.error.BizError;

/**
 * 业务异常
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public class BizException extends RuntimeException {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(BizError message) {
        super(message.getMessage());
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}