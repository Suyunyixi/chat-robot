package club.suyunyixi.robot.domain.command;

import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.exception.CanNotParseCommandException;
import club.suyunyixi.robot.infrastructure.exception.InsufficientPermissionsException;

/**
 * 机器人命令处理接口, 此处为处理命令的模板
 *
 * @author Suyunyixi
 * @date 2023/2/6
 */
public interface CommandHandler {
    /**
     * 解析消息, 并执行独立业务处理
     */
    BaseRespMessage explain(BaseParam param, BaseContext data) throws CanNotParseCommandException, InsufficientPermissionsException;
}