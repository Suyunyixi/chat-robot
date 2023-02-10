package club.suyunyixi.robot.infrastructure.exception;

/**
 * 无需处理时抛出此异常
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public class NeedNotHandleException extends Exception {

    public NeedNotHandleException() {
    }

    public NeedNotHandleException(String message) {
        super(message);
    }
}