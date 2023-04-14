package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.infrastructure.command.chain.BaseChain;
import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseParam;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.entity.enums.base.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.infrastructure.entity.constants.ChinaConstant.*;

/**
 * 权限判断链
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:44
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = LEVEL_2, parent = LEVEL_1)
})
public class PermissionChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        // TODO 权限处理, 目前不考虑
        return nextChain(END, param.getSource()).handle(param, data);
    }
}