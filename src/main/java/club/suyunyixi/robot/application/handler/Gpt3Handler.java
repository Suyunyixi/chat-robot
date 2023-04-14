package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.infrastructure.command.handler.AbstractHandler;
import club.suyunyixi.robot.infrastructure.common.base.BaseContext;
import club.suyunyixi.robot.infrastructure.common.base.BaseParam;
import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.entity.enums.base.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * gpt-3(maybe 4?)处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.CHAT_GPT_3_QUEST)
public class Gpt3Handler extends AbstractHandler {

    @Resource
    private OpenAiClient openAiClient;

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        CompletionResponse completions = openAiClient.completions(param.getReqMessage());
        return BaseRespMessage.reply(data, Arrays.stream(completions.getChoices()).map(Choice::getText).collect(Collectors.joining("\n")));
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "查询失败了捏, 狗修金sama");
    }
}