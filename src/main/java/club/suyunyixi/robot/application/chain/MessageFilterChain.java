package club.suyunyixi.robot.application.chain;

import club.suyunyixi.robot.domain.command.BaseChain;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import club.suyunyixi.robot.infrastructure.exception.NeedNotHandleException;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.domain.entity.constants.ChinaConstant.*;

/**
 * 消息过滤链, 主链
 *
 * @author Suyunyixi
 * @date 2023/2/10 17:58
 * @email xukai@co-mall.com
 */
@Slf4j
@Service
@ChainService(lines = {
        @ChainService.ChainLine(branch = MessageSource.GROUP, name = BaseChain.MAIN_CHAIN_KEY)
})
public class MessageFilterChain
        extends BaseChain<BaseParam, BaseContext, BaseRespMessage> {
    @Override
    public BaseRespMessage handle(BaseParam param, BaseContext data) {
        // todo 后期切换成模式
        // 不@bot不处理
        ExceptionUtil.throwIfTrue(CharSequenceUtil.isBlank(param.getAt()), new NeedNotHandleException());
        return nextChain(LEVEL_1, param.getSource()).handle(param, data);
    }
}