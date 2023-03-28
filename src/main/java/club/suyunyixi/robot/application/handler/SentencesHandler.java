package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.application.query.AOQuery;
import club.suyunyixi.robot.application.query.UmongQuery;
import club.suyunyixi.robot.domain.command.handler.AbstractHandler;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.dto.ao.APSentencesDTO;
import club.suyunyixi.robot.domain.entity.dto.uomg.UomgMusicDTO;
import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.collection.ListUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static club.suyunyixi.robot.infrastructure.error.BizError.REMOTE_ERROR;

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