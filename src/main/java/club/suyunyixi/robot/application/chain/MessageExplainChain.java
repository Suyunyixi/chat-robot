package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.application.MessageExplainApplication;
import club.suyunyixi.robot.domain.command.chain.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.domain.entity.enums.base.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import cn.hutool.core.lang.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

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
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = BaseChain.MAIN_CHAIN_KEY)
})
public class MessageExplainChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Resource
    private MessageExplainApplication explainApplication;

    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        // 解析
        Pair<MessageHandler, String> explain = explainApplication.explain(param);
        // 添加处理器
        data.setHandler(explain.getKey());
        data.setReqKeyword(explain.getValue());
        return nextChain(LEVEL_1, param.getSource()).handle(param, data);
    }
}