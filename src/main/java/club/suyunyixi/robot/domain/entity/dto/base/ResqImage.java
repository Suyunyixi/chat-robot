package club.suyunyixi.robot.domain.entity.dto.base;

import club.suyunyixi.robot.domain.entity.enums.base.ImageType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Suyunyixi
 * @date 2023/2/11 20:20
 **/
@Data
@Accessors(chain = true)
public class ResqImage {
    private ImageType type;
    private String key;
}