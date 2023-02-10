package club.suyunyixi.robot.application;

import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import club.suyunyixi.robot.infrastructure.exception.CanNotExplainException;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消息解析
 *
 * @author Suyunyixi
 * @date 2023/2/10 17:37
 */
@Slf4j
@Component
public class MessageExplainApplication {
    public MessageHandler explain(BaseParam param) {
        String message = param.getReqMessage();
        // 字符开头匹配模式
        MessageHandler handler = StrUtil.getStartWith(message);
        // 无法解释
        ExceptionUtil.throwIfTrue(ObjectUtil.isNotNull(handler), new CanNotExplainException());
        return handler;
    }
}