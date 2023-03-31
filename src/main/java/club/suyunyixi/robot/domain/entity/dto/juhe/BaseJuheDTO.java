package club.suyunyixi.robot.domain.entity.dto.juhe;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:11
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class BaseJuheDTO<T> {

    @JsonAlias("reason")
    private String reason;
    @JsonAlias("result")
    private T result;
    @JsonAlias("error_code")
    private Integer errorCode;
}