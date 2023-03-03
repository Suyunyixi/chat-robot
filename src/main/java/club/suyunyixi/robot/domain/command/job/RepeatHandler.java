package club.suyunyixi.robot.domain.command.job;

/**
 * @author Suyunyixi
 * @date 2023/3/3 17:23
 * @email xukai@co-mall.com
 */
public interface RepeatHandler {
    /**
     * 是否重复校验
     */
    boolean isRepeated(String str);
    void setRepeat(String str);
}