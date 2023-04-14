package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.application.query.AOQuery;
import club.suyunyixi.robot.infrastructure.command.handler.AbstractHandler;
import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseParam;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.entity.dto.ao.APSentencesDTO;
import club.suyunyixi.robot.infrastructure.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.collection.ListUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.infrastructure.exception.error.BizError.REMOTE_ERROR;

/**
 * 推荐音乐处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.SENTENCES)
public class SentencesHandler extends AbstractHandler {
    @Resource
    private AOQuery query;

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        data.setRespAts(ListUtil.toList(param.getAt()));
        APSentencesDTO dto = query.sentences();
        ExceptionUtil.throwIfTrue(dto == null, REMOTE_ERROR);
        assert dto != null;
        return BaseRespMessage.reply(data, dto.toString());
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "查询Uomg失败了捏, 狗修金sama~");
    }
}