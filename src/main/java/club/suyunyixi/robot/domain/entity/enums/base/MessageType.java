package club.suyunyixi.robot.domain.entity.enums.base;

import lombok.NoArgsConstructor;

/**
 * 消息类型
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:05
 **/
@NoArgsConstructor
public enum MessageType {
    /**
     * 好友私聊
     */
    AT,
    /**
     * 群聊天系统设置
     */
    GROUP_SYSTEM,
    /**
     * 额外的聊天内容, 包含自定义的触发关键字, 及指定回答
     */
    EXTRA,
    /**
     * 定时任务
     */
    TASK,
    ;
}