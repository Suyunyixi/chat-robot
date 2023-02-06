package club.suyunyixi.robot.infrastructure.exception;

/**
 * 当消息发送失败时抛出此异常
 *
 * @author Suyunyixi
 * @description
 * @date 2023/2/6
 */
public class CanNotSendMessageException extends Exception {
    public CanNotSendMessageException(String message) {
        super(message);
    }
}