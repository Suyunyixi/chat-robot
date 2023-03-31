package club.suyunyixi.robot.domain.entity.job.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Suyunyixi
 * @date 2023/3/3 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BiliBiliFollowMessage {
    private String message;
}