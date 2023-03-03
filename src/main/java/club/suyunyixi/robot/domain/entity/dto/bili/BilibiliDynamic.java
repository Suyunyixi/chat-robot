package club.suyunyixi.robot.domain.entity.dto.bili;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * Bilibili 动态更新实体类，动态不单单指 UP 个人中心动态，也可以泛指番剧更新或其他内容更新
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BilibiliDynamic {
    /**
     * 更新时间
     */
    @JsonAlias({"pub_ts", "pub_time"})
    private Long pubTime;
    /**
     * 推送原因
     */
    @JsonAlias({"pub_action", "share_copy"})
    private String pubAction;
    /**
     * 作者或内容名称
     */
    @JsonAlias({"name", "season_title"})
    private String authName;
    /**
     * 发布时头像
     */
    @JsonAlias("face")
    private String face;
    /**
     * 封面，如果有多张，只会选取第一张
     */
    @JsonAlias("covers")
    private String cover;
    /**
     * 描述
     */
    @JsonAlias("long_title")
    private String desc;
    /**
     * ID
     */
    @JsonAlias({"live_od", "bvid", "season_id"})
    private String id;
    /**
     * 跳转链接
     */
    @JsonAlias({"short_link", "link", "jump_url"})
    private String jumpUrl;

    private Integer type;

    private List<String> images;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}