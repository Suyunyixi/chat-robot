package club.suyunyixi.robot.infrastructure.entity.dto.baidu.map;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/10 11:46
 */
@NoArgsConstructor
@Data
public class BaseMapDTO<T> {

    @JsonAlias("status")
    private Integer status;
    @JsonAlias("message")
    private String message;
    @JsonAlias("result")
    private List<T> result;
}