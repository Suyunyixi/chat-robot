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

    public static final String TOKEN = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..cpuIPttyIMVvvCU4.XLRubFRty8AnO0-u0jB5wIBzAWEDHsdZo1VrBE6InsNtTFd0BhXHeLV7gm1IkYGYesocXBBki-NIfZeXn7RwY-qBrfuJx-3CLs9YTtUCDTWytrnlMtHjIlebc5S6Mf6b_YwQH6yxWSBrTApuUnYXgaeaSQ0dHGboFZXdcz39p8RNykjTVqEHoMmba29wo_EhKt0qRL-RrzUuhXCw-ZBpbLTgFlzTRS3tK_DuhISt4oGvz1swbsZq1piuTxgpg6uZZoCc8VLvnjmO1_3aY4ZjdJjnPmHfTec4ND1yKEzk2miLvXeGaDKqgUqOrjG2vsVT8XHyMz4Ngecud03sjf8cRZoythxDIVeu8aqYGrwy81h0zEVfpMH4DHg-VJ_1DWxeYtxji1AhrwExsfwTXSNcFhFYmmZzU3GMZYOSX2tGPsJrc0qTHCATYqJnV3YRDvQelWDGKujo9yvAH9BW-srhsz0wwK4l-O3t7Y0rLyCK2wPiLiYfTe3Rd4TmyypeATRvMlcJL4FJ6IC6oAyVFFtjtbRvzqNyNcfAT2eeX6JE-zVVCLO53eEuQWqK1MUQihoJ2ttwah_BaWOcBYqkvPKvrglX-us7LifJ7KPlVXMnD1wn1OV54QrBGtmkLa2e5E5R7crak2d59kuCMnBkNjlqDu_ot93C9lnJhEmMBCymndbe9QPjq3wWJ4GItpFmZggkWISRMWG3_UJmdu7DbHa2zuuO1xlbd4wE7LeAPMphdIXel0-WiLIZsCQSm9ZBHHEO2lpuOqryxa48xxn3tIptEu-BRz1cP-pZ1fjDDjRR4hYtwvlZG9pnd19UZ-_l15o29lf-wuEYuP7jGGVlVoQzsyypDPdr5ZJ6-TUD3Ukejd-3ewQksLaxWONJTzR2dmvnlv6vSm6jGKSKw0s6Oq41UQznd3UjvfV9NdTgdUhHe-oNKlvFAKE8iynfKH1QF2kYDwIxG842dR7Jjpuun_oZy1Sk2xx03Me2qNKoWbnA4Qy0mT7mTg78yEgtkQQLCaDV08OTllDBxoGHGhIBD0aopaOit5R6YZnnAyrz3pu-Y-PvlFbIAvEtFnA4-8DybKJqRrYzSIon7ex2dRpwJ8aps51e5CxRZqoWdjMvICS-raTFXanlRB_ZnmkuLW3PZ8gpN0OWb5DcaIp_iZqi3JMnD_rrKiHS8vi-rSlH4XLRB3lvvtbpZyBhGX-TeZtW7tdtlgVIeg_lKm1qKgHqQ1fPnGVLrXfTrSL-YoYtSSSZ5hawdPhPfPu7w1UI57eLJTWo4aYY036DkkipWcCDLr5eZs0T7u5fmjVB_LUm_xUIM0suzTzqVUP1Xs3i0iCwV2OMFAZwVYYPcxak1NpAb8bQnL88SBtzsqzxkkdL_V-r9mp15v0r6fbpd3W6B0V5s9g2XBOIn1DQhoyS5Em_PJvhXP4b4ChR8R6YqBIl8VD3PXj6P2fuxwHZ69kOB_48WjRanZr1UkbDwl5zvaM1QvtU2iNyN0CEpR0dTCQrr8_DB2mK_LJzYQCJAu3HS33Rb7DNNFuKLfqwdlF6HE22ybUOBoHh37lZmP_mpQM0MS995IBPUXb9CWvsofHnY_p6UDgQcPouRQpGr2NVSq6dW6OWwI1ldnVWAA16pVloXqhEZezebgJw2mTihiCPGRXH5V312hb-K34oYi1fhzgW_Tnn-o5Wmic9fzJmcVlhQ2F8lyyD5JTV4cYLSpp-fbo4bi-NrwB6nYoT5FIAqveAb-5LKX_GmaJAfSuB6gYAxqQhBp6xmzT4gJ82TuREEKf-BaLzAv7StmtoCpRDNIM5pYsIsIOu36eF83mhPeWUB7u1IOB1Y0wuOzhWrF41uTUi4j7250jZMFzieGuopRre9GbeR_RTgM126k1it1_b4cuqcNLzl-FtBE2jw6AzkfNhuO-8xTVCkfBkeC2S5TyY6r3Rr5leOecprqZBpAyQCM5Ka1FbxPHPPePsnp3Pk-yTvjzlfCvN7Zgrbqzw9EDGg06UGAFxsfsTxhdfD4ceJHmmfllN6xwVNbrgfqM20aCn_y60zk6kxpQn8I0OiLmHvW7ansC2GBF1wKvjpnWL_eXt2CqFtoxCuQsR9wvTqFifOhISynEUGIZJsRLhJLlaaXoEFz5tlfw19pdrgtkTtrSBrNpS97V0m_ezF9b8tL48dhAcnW3F2P_kFtKtWQfZbzhcPyC4a9Eh4a7c0W5ViiYaQFAlQti4Ii6z8CbqfLisRFAl5Cm7H-yND4M3ayKJJAcFQYL_D6kKM9_uOP7qVh8WOFo-tHg1U8B9ayMXa-C5d7VwfBP5aF9eJlYnNc970jd9qzFCdS4cQ461aerE_iC6Xt2zd8q1tDnQBnQTEgZizhWulAZNN1FELQiayoEugjD4T5zt_M2AO_lBSWLurDLlszWUI4WVr1rCx8lOoKqnuPgbZgksYM8qUeVwEC-804BQzXlJX92fKWv5EWgjVizmN01QsrwwQJMOjkNZcACrCJGwLtN16RR28dx5ds_HIfDl6LKPVtJ0sjU77oAYrU5QJ15yzr6AwjEwR950iJbsLnu5gSGJr2mBLrJeIyL-iB-2j6S_E_kGE7RnRsstQfTDpYthtRnCdOPQ3AJ_UX48-t0HAPB0mS3p6g.k2pn2u9mxX4Jgi8BVFoNUQ";
    public static final String CF_CLEARANCE = "j3TpF.bfV7COZ6A_W1EhRRY33PVXn_pQhCC_PdBp4kY-1677723306-0-1-dbcb6581.bbac1e0d.8cca3a61-250";
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