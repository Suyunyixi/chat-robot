package club.suyunyixi.robot.infrastructure.entity.dto.uomg;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suyunyixi
 * @date 2023/3/13 11:02
 * @email xukai@co-mall.com
 */
@NoArgsConstructor
@Data
public class UomgMusicDTO {
    private String name;
    private String url;
    private String picurl;

    public String toString() {
        return CharSequenceUtil.format(
                "歌曲名称: {}\n" +
                        "歌曲地址: {}\n" +
                        "图片地址: {}\n", name, url, picurl);
    }
}