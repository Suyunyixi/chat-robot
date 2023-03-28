package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.application.query.AOQuery;
import club.suyunyixi.robot.application.query.UmongQuery;
import club.suyunyixi.robot.domain.command.handler.AbstractHandler;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.dto.ao.APVideoDTO;
import club.suyunyixi.robot.domain.entity.dto.uomg.UomgMusicDTO;
import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static club.suyunyixi.robot.infrastructure.error.BizError.REMOTE_ERROR;
import static cn.hutool.core.text.StrPool.COMMA;

/**
 * 推荐音乐处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.SHORT_VIDEO)
public class ShortVideoHandler extends AbstractHandler {
    @Resource
    private AOQuery query;

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        data.setRespAts(ListUtil.toList(param.getAt()));
        List<APVideoDTO> dtos = query.shortVideos();
        ExceptionUtil.throwIfTrue(CollUtil.isEmpty(dtos), REMOTE_ERROR);
        return BaseRespMessage.reply(data, replyMsg(dtos));
    }

    private String replyMsg(List<APVideoDTO> dtos) {
        return dtos.stream().map(APVideoDTO::toString).collect(Collectors.joining(COMMA));
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "查询API失败了捏, 狗修金sama~");
    }
}