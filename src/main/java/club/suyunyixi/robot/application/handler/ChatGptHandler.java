package club.suyunyixi.robot.application.handler;

import club.suyunyixi.robot.domain.command.AbstractHandler;
import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.domain.entity.enums.MessageHandler;
import club.suyunyixi.robot.infrastructure.anno.MessageHandlerStrategy;
import cn.hutool.core.collection.ListUtil;
import com.github.plexpt.chatgpt.Chatbot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 阿P处理器
 *
 * @author Suyunyixi
 * @date 2023/2/11 19:54
 **/
@Slf4j
@Service
@MessageHandlerStrategy(handler = MessageHandler.CHAT_GPT_QUEST)
public class ChatGptHandler extends AbstractHandler {

    public static final String TOKEN = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..SfjQSwb57UkKigZR.I2rPMMtEcHUxeAj-i83pggvIdObXDn6lmFgkg-vgro5YqAuvM0HOZIF5WOc04O2iVP1aBbtm4-dQ1xR3iZo-pEY9ZLxNQKlgjnrUHwKFZHJtbimvC5WJps3bpchxvSiIVgnMQ5vNBD5iZm9hAgf0LmV7zyhXxhoF-TjZTfGiuADMoExvplaLXDqw2QHr-cA5uVNXAIVVs_Krsivx8yYfQsR_ycD5kqH2_0NfhfXHz5a4Ht1_J5Kh0HJCZiAYW_p5GMiS5oR0J7LYUR5gEK5KZGWH2wFWwBhEl388JgbDranNl_eq9s9Ubpr6__bkY1THD3GGnTosVZwmxk_0uQM9tDu4XB8kWlUCIiSYOOXj59UHgUOKjfzxBACJ7Kid6IzVomgNVWcIF09nRjOIqpZvfmmOv1Un0maPUbY4aDnq9d-kcAh9LvqiAWkV3HqSXvHQryrbo7v80WWUOJqXWDF-PYZBpJo0LwrFwRLFC7mKg8Ub3wsoSfLx4xuSBsgcWbEIUcHNWKExJr_gQFa1rdiBNLUi-A1sK2zEhV2Sjj3-9xlbtAveBiSVOdxhMuQgQcX7ELFKArTno9fzGO2jcCdtojwvtWgDIQelY0f3FIL50HePGPgIo0ewir8kcItZBBUCxcpZ2cBRWo0fKEt8rWZvdmeI-sSUTbi1M4qwO3X9gaoOUTq0pu2HaxVf-wn0j2gBQwC1ZsTQosdNLVSFQZwhGmTvwTSjeBXf0HOPQaY44ZyC1G2-JiGrStLprK6QyGRIr_wFzcXmv9u8PgEU8T6-sUA3xd1YwQNjE64ximGtCBYo4uKLrVhIrylBSAWZF8TjH3z-XC9OYHkiG5N-zK3WXs1eVbr2TDcNcOYrKAPKU_xFvklO8GU9HTDI6qm_ws1-GEcGMtCTBtA7ix7PArnxcfqFTg_ez_l61qmmFxo5njIjOFyNU6_Lyc3gmWmeuhnnIr5-3y7xxWmTDwZoaxUkl-EUP5gLE39ldHdKjtoN4QjTL1M81cEcX3KFrX34x5OSuXQU4jve6mjjUA0ZcU2iOaHne6jt45VW6tuN0tjb1XptvWrhXJbWnkfL1abGd-ZwKkbIsHLXqBRBeqrV164OGtTM4I6NO6p2sIfKb_XFFq1_e7T4z0TDB_cGlqAeGqCM-rHMucIJ9vEARhKeMgeyGDkly4uUBGQTJaXokF1k-jSOYTYimDymPiXXpzFVA73g-j3K3cbyvfNvWWSmNgkdPeSRPN7qoqGFaBEDwJESTcI4R2jsTSA9BaC-ce1MFTTo1RdrXS0ydhX4T7dGKENiQvmiBQrKgMMlAs24J1otogBxm09sMLnqhZEx8kuvP9nR6qzHbajduZkMPEiAkXtEE16QFvngT3BOj1tycGUie5_7ccZVs9zvvs4kMPRXgU_rh4MPJHlP2ZGBDyKSnf0GARVXJWXF36otGazsi-a4WepEuALT0620C2sm1zGT5t-HSg5I0pAvDSkF3h1s4FH4FJu8G54O-FpyLWq7ohEcLOsjEiPPKNlhBg4F2CuB9ZaU9lLPdXTIvK1asiJaeAo0oDKH3sQBvuiOrmajHHsXkJeZ0VxM03TtnGNPmpwWbbDsp4cXZMjBPJphuVkwxD6Xn_XMGt5o023werWthBCreKl20lIaSbeElpYQo8vU1Cdy5CLNCKQsAp_hDTMnRsBCjx-QWeTVXjsGrKTU47tQEzS9bGWm_GALg2gC-cHBvM_y2tWvy09wTQTTWJU_KpofimioFidBCieb54J_NPbFjb4yPmvKVrk42qOk310-TP7-NUdrigOHJPUGeR5YqpfdiyuOJp-iaVnRWONBMwgQOYFjdP1tj4G8PHXsj8dMsja-mICyDvVNQ-4z9OSqDKEUYqYQB7dHKrcf3G0y6maKcqfAXIRzvLZRZFuuwgHmrTs65f0mfoRGenJD-MwK02mKsd8sFruRd0wvIUBYJkt2Fh-jwHVK66uU3mxD6fJmX9cCxztA8cZSz10sFaF5oYUeObFyKANoFN11dPkqgHimbQ-Hhi-L-FwSmxZEZ-p9ngCqHSEUsWffktH0feDBcBqzc5n9h5WBt0QxA43W1zuExgfnKXls-ZhT5KSpDfFGfbti6-3N20XRxZiT4QjYn3kYxeUUFbBpkJ-pK8r6lHLO97NJo7Y6qs0E09Vugxv6nNjuxF012Rdquyum3r8FZQoMJfpWvKsjvH6kBDZTFZu-_Otw9y5nWtQBI8qh35I8_SQTvZ5eB4YJojIRwqPkd3xCQ3NTz--2zB29ij2iIJ3lW2xf7Nhhp8-B4a7NMCs_87lYIRcPzS_6d6I7AvkKnfhk4rm9fCboLP53NyhQ9o1Pirnlo1QvxVcRaNyHU9l5lQ-jV_CKQ7XNrDSuLUtqq32Vqq4YgNikp4RKiQ0vqM66gPQl1MZFoOv3uUbXBk5ZUdFpL89FG2uyMg-_Q_k1KHL9cKpLNV1MB9LAQVkZyX1_icVEBHYBxpngTmxzA5he0D6n6WCwXM-WaEA6gKEWJWwPZFL9FoXGyqa9v4A-ICM9jeacr3ssIG_y0W604pB2y5JBeRmhM-p6xd6nm_SPoNlQ77LeKqsRfusSPyxBw1-z8qrwwrsZcXTVqqFlXK0RFG2Y_UzM3Vbpdw.Tr2633ONYlAJ9tAoQWmYqQ";
    public static final String CF_CLEARANCE = "MmZLK3G61GjZE0awXp.eLbFyWaMwRBYDlkmF166d7cg-1677724819-0-1-dbcb6581.bbac1e0d.8cca3a61-160";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36 Edg/110.0.1587.57";

    @Override
    public BaseRespMessage explain(BaseParam param, BaseContext data) {
        Chatbot chatbot = new Chatbot(TOKEN, CF_CLEARANCE, USER_AGENT);
        chatbot.setHost("http://www.suyunyixi.club:5000");
        Map<String, Object> chatResponse = chatbot.getChatResponse(param.getReqMessage());
        data.setRespAts(ListUtil.toList(param.getAt()));
        return BaseRespMessage.reply(data, chatResponse.get("message").toString());
    }

    @Override
    public void success(BaseRespMessage rep) {
        log.info("send success");
    }

    @Override
    public BaseRespMessage fail(BaseContext context) {
        log.info("send fail");
        return BaseRespMessage.reply(context, "查询失败了捏, 狗修金sama");
    }
}