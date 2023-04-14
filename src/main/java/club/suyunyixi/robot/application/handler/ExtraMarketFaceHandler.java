package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.infrastructure.command.handler.AbstractHandler;
import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseParam;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 阿P处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.EXTRA_MARKET_FACE)
public class ExtraMarketFaceHandler extends AbstractHandler {

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        return BaseRespMessage.replyMarketFaceOnly(data.setRespAts(ListUtil.toList()), param.getMarketFaces());
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "红豆泥私密马赛, 又");
    }
}