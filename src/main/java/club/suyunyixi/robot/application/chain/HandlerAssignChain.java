package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.domain.command.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.domain.entity.constants.ChinaConstant.*;

/**
 * 处理器下分链
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:44
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = END, parent = LEVEL_1)
})
public class HandlerAssignChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {

        return null;
    }
}