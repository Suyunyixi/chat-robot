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

    CHAIN_IS_VULNERABLE(10161003, "chain is vulnerable, not found nextChain"),
    SEARCH_ES_ERROR(10161004, "to search es had a error"),
    ;

    private final int code;
    private final String message;
}