package club.suyunyixi.robot.domain.entity.base;

import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用于传递的上下文对象
 *
 * @author Suyunyixi
 * @date 2023/2/6 22:11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseContext {
    private MessageHandler handler;

    public static BaseContext empty() {
        return BaseContext.builder().build();
    }
}