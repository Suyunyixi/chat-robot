package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.domain.command.AbstractHandler;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 阿P处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.CHAT_GPT_3_QUEST)
public class Gpt3Handler extends AbstractHandler {

    public static final String API_KEY = "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";

    private final OpenAiClient openAiClient = new OpenAiClient(API_KEY, 60, 60, 60);

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        CompletionResponse completions = openAiClient.completions(param.getReqMessage());
        return BaseRespMessage.reply(data, Arrays.stream(completions.getChoices()).map(Choice::toString).collect(Collectors.joining("\n")));
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