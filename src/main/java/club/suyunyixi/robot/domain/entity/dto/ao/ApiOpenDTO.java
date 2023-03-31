package club.suyunyixi.robot.domain.entity.dto.ao;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/13 14:04
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class ApiOpenDTO<T> {
    @JsonAlias("code")
    private Integer code;
    @JsonAlias("message")
    private String message;
    @JsonAlias("result")
    private T result;
}