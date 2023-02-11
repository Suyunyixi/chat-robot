package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.application.query.PixivQuery;
import club.suyunyixi.robot.domain.command.AbstractHandler;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 阿P处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.PIXIV)
public class PixivHandler extends AbstractHandler {
    @Resource
    private PixivQuery query;

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        data.setRespAts(ListUtil.toList(param.getAt()));
        return BaseRespMessage.reply(data, query.search(data.getReqContent(), Boolean.TRUE));
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "查询阿P失败了捏, 狗修金sama");
    }
}