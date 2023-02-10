package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.application.MessageExplainApplication;
import club.suyunyixi.robot.domain.command.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static club.suyunyixi.robot.domain.entity.constants.ChinaConstant.*;

/**
 * 消息分析链
 *
 * @author Suyunyixi
 * @date 2023/2/7 10:44
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = LEVEL_1, parent = BaseChain.MAIN_CHAIN_KEY)
})
public class MessageExplainChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Resource
    private MessageExplainApplication explainApplication;

    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        // 添加处理器
        data.setHandler(explainApplication.explain(param));
        return nextChain(LEVEL_2, param.getSource()).handle(param, data);
    }
}