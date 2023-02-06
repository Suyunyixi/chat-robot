package club.suyunyixi.robot.infrastructure.exception;

/**
 * 当操作权限不足时抛出此异常
 *
 * @author Suyunyixi
 * @description
 * @date 2023/2/6
 */
public class InsufficientPermissionsException extends Exception {

    public InsufficientPermissionsException() {
    }

    public InsufficientPermissionsException(String message) {
        super(message);
    }
}
