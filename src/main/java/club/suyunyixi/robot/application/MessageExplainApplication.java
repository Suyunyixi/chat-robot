package club.suyunyixi.robot.application;

import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.exception.CanNotExplainException;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import love.forte.simbot.message.Message;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * 消息解析
 *
 * @author Suyunyixi
 * @date 2023/2/10 17:37
 */
@Slf4j
@Component
public class MessageExplainApplication {
    public MessageHandler explain(BaseParam param) {
        String message = param.getReqMessage();
        // 字符开头匹配模式
        MessageHandler handler = StrUtil.getStartWith(message);
        // 额外处理表情包模式
        handler = explainForMarketFace(handler, param);
        // 无法解释
        ExceptionUtil.throwIfTrue(ObjectUtil.isNull(handler), new CanNotExplainException());
        return handler;
    }

    /**
     * 额外处理表情包模式
     */
    private MessageHandler explainForMarketFace(MessageHandler handler, BaseParam param) {
        Map<String, Message.Element<?>> marketFaces = MapUtil.newHashMap();
        // 额外处理市场表情
        if (CollUtil.isNotEmpty(param.getMarketFaces())) {
            Set<String> keywords = MessageHandler.EXTRA_MARKET_FACE.getKeywords();
            // 筛选出中标的市场表情
            param.getMarketFaces().forEach((k, v) -> {
                if (keywords.contains(k)) {
                    marketFaces.put(k, v);
                }
            });
            // 如果存在处理市场表情 && (之前没有handler || 之前的handler级别高于额外表情)
            if (CollUtil.isNotEmpty(marketFaces)
                    && (ObjectUtil.isNull(handler)
                    || handler.getLevel() > MessageHandler.EXTRA_MARKET_FACE.getLevel())) {
                handler = MessageHandler.EXTRA_MARKET_FACE;
                param.setMarketFaces(marketFaces);
            }
        }
        return handler;
    }
}