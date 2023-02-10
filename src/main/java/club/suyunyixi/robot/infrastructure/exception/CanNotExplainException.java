package club.suyunyixi.robot.infrastructure.exception;

/**
 * 无法解析时抛出此异常
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public class CanNotExplainException extends Exception {

    public CanNotExplainException() {
    }

    public CanNotExplainException(String message) {
        super(message);
    }
}