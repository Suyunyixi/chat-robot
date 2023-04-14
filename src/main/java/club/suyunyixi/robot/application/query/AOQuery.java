package club.suyunyixi.robot.application.query;

import club.suyunyixi.robot.infrastructure.client.AOClient;
import club.suyunyixi.robot.infrastructure.entity.dto.ao.APSentencesDTO;
import club.suyunyixi.robot.infrastructure.entity.dto.ao.APVideoDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Suyunyixi
 * @date 2023/3/28 14:51
 * @email xukai@co-mall.com
 */
@Component
public class AOQuery {
    @Resource
    private AOClient client;

    public List<APVideoDTO> shortVideos() {
        return client.videoPage(1, 10).getResult().getList();
    }

    public APSentencesDTO sentences() {
        return client.sentences().getResult();
    }
}