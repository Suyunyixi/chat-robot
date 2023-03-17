package club.suyunyixi.robot.domain.entity.dto.uomg;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/13 10:55
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class UomgDTO<T> {
    private String code;
    private String msg;
    @JsonAlias({"content", "data"})
    private T data;
}