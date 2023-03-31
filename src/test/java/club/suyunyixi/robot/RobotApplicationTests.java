package club.suyunyixi.robot;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class RobotApplicationTests {

    @Test
    void contextLoads() {
        //配置api keys
        OpenAiClient openAiClient = new OpenAiClient("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",60,60,60);
        CompletionResponse completions = openAiClient.completions("我想申请转专业，从计算机专业转到会计学专业，帮我完成一份两百字左右的申请书");
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }
}
