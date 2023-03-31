package club.suyunyixi.robot.domain.entity.dto.ao;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/13 14:06
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class AOPageDTO<T> {
    @JsonAlias("list")
    private List<T> list;
    @JsonAlias("total")
    private Integer total;
}