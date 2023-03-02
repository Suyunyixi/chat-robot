package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.domain.command.chain.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import club.suyunyixi.robot.infrastructure.register.HandlerRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = END, parent = LEVEL_2)
})
public class HandlerAssignChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Resource
    private HandlerRegister handler;

    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        return handler.get(data.getHandler()).execute(param, data);
    }
}