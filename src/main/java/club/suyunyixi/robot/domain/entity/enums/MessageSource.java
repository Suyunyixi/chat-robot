package club.suyunyixi.robot.domain.entity.enums;

import lombok.NoArgsConstructor;

/**
 * 消息来源
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:05
 **/
@NoArgsConstructor
public enum MessageSource {
    /**
     * 好友私聊
     */
    FRIEND,
    /**
     * 群聊天
     */
    GROUP,
    ;
}