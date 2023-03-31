package club.suyunyixi.robot.domain.entity.dto.ao;

import cn.hutool.core.text.CharSequenceUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/13 14:05
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class APVideoDTO {

    @JsonAlias("coverUrl")
    private String coverUrl;
    @JsonAlias("duration")
    private String duration;
    @JsonAlias("id")
    private Integer id;
    @JsonAlias("playUrl")
    private String playUrl;
    @JsonAlias("title")
    private String title;
    @JsonAlias("userName")
    private String userName;
    @JsonAlias("userPic")
    private String userPic;

    public String toString() {
        return CharSequenceUtil.format(
                "标题: {}\n" +
                        "地址: {}",
                title,
                playUrl
        );
    }
}