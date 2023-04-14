package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.infrastructure.command.chain.BaseChain;
import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseParam;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.entity.enums.base.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import club.suyunyixi.robot.infrastructure.exception.NeedNotHandleException;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 消息过滤链, 主链
 *
 * @author Suyunyixi
 * @date 2023/2/10 17:58
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = LEVEL_1, parent = BaseChain.MAIN_CHAIN_KEY)
})
public class MessageFilterChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        if (!data.isExtra()) {
            // 不@bot不处理 todo 后期切换成配置模式
            ExceptionUtil.throwIfTrue(!param.isAtMe(), new NeedNotHandleException());
        }
        return nextChain(LEVEL_2, param.getSource()).handle(param, data);
    }
}