package club.suyunyixi.robot.domain.entity.job;

import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/2 17:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JobFriendRespMessage extends BaseRespMessage {
    /**
     * 回复的私人好友
     */
    private List<String> friends;
}