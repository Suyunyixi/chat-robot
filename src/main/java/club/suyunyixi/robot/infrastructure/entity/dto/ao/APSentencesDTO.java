package club.suyunyixi.robot.infrastructure.entity.dto.ao;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/13 14:15
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class APSentencesDTO {
    private String name;
    private String from;

    public String toString() {
        return CharSequenceUtil.format(name + "  ———— " + from);
    }
}