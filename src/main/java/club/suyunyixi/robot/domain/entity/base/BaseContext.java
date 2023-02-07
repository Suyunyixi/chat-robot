package club.suyunyixi.robot.domain.entity.base;

import lombok.Data;

/**
 * 用于传递的上下文对象
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:11
 **/
@Data
public class BaseContext {
    /**
     * 消息主体
     */
    private String message;
    /**
     * 发起人
     */
    private String questioner;
}