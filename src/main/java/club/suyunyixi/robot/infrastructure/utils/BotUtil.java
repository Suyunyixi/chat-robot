package club.suyunyixi.robot.infrastructure.utils;

import club.suyunyixi.robot.infrastructure.common.base.BaseRespMessage;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ObjectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import love.forte.simbot.Identifies;
import love.forte.simbot.component.mirai.message.MiraiSendOnlyImage;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.At;
import love.forte.simbot.message.Message;
import love.forte.simbot.message.Messages;
import love.forte.simbot.message.Text;
import love.forte.simbot.resources.Resource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author Suyunyixi
 * @date 2023/2/9 11:47
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotUtil {

    /**
     * 是否@Bot
     *
     * @param event {@link GroupMessageEvent}
     */
    @SuppressWarnings("unchecked")
    public static boolean isMe(GroupMessageEvent event) {
        Messages messages = event.getMessageContent().getMessages();
        if (ObjectUtil.isNotNull(messages)) {
            for (Message.Element<?> element : messages.toList()) {
                if (element instanceof At at && event.getBot().isMe(((At) element).getTarget())) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 将结果组装成消息
     *
     * @param rep {@link BaseRespMessage}
     * @return {@link Messages}
     */
    public static Pair<Boolean, Messages> assemble(BaseRespMessage rep) {
        List<Message.Element<?>> messageList = ListUtil.toList();
        // 添加at
        Optional.ofNullable(rep.getAts()).ifPresent(list -> list.forEach(at -> messageList.add(new At(Identifies.ID(at)))));
        // content
        messageList.add(Text.of(rep.getContent()));
        // todo 添加images, 后期优化
        Optional.ofNullable(rep.getImages()).ifPresent(list -> list.forEach(image -> messageList.add(image(image.getKey()))));
        //添加商店表情
        Optional.ofNullable(rep.getMarketFaces()).ifPresent(list -> list.forEach((k, v) -> messageList.add(v)));
        return new Pair<>(CollUtil.isEmpty(rep.getAts()), Messages.listToMessages(messageList));
    }

    public static MiraiSendOnlyImage image(String imagePath) {
        Path path = Paths.get(imagePath);
        return MiraiSendOnlyImage.of(Resource.of(path));
    }
}
