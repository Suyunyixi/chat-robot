package club.suyunyixi.robot.infrastructure.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Suyunyixi
 * @date 2023/2/7 11:30
 */
@Getter
@AllArgsConstructor
public enum BizError {

    CHAIN_IS_VULNERABLE(1000001, "chain is vulnerable, not found nextChain"),
    HANDLER_NOT_FOUND(1000002, "handler is vulnerable, handler not found"),

    REMOTE_ERROR(1100001, "remote.error"),
    ;

    private final int code;
    private final String message;
}