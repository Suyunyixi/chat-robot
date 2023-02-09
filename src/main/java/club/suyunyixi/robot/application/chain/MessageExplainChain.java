package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.domain.command.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRepMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.domain.entity.constants.ChinaConstant.LEVEL_1;

/**
 * 处理消息类型链, 主链
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:44
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = BaseChain.MAIN_CHAIN_KEY)
})
public class MessageExplainChain
        extends BaseChain<BaseParam, BaseContext, BaseRepMessage> {
    @Override
    public BaseRepMessage handle(BaseParam param, BaseContext data) {
        // TODO 权限处理, 目前不考虑
        return nextChain(LEVEL_1, param.getSource()).handle(param, data);
    }
}