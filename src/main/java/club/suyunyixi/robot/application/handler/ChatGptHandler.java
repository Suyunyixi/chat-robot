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

    public static final String TOKEN = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..6IGJsQJ8dKlE9dqP.kYeIDC5qZUjef4uiq20GG1QvafJtonQoD7YbEaWgUNIy8Vclcbo4WxO9N4cvoL4uDdeoTsr8SVkAt4lIKRjBjXNhSiHnfI8EYTw0TeuVlMkZD3oAUTD_LNTzY3rG9xmziwTX18e8NJVcQB8GzEuOmu1bK1QktrFaaLdGNIiX-d-TflDJrFAq31h5eTtGwYrR7E6C1pn_UXa1hC9bOFymw8YvddZLNkJ_GPIHZLro_6Jpld4hPt4riDaNoQ5qNoA8l7ujMt6QjeV10TbgofpSMJZDRGN5IS6icg0Zn36lrMWU7yuCb16JVaDod8EmsVwayy7elEvEwZXJ-MRNUPn9MTRNes8uYVoxR_PJrjzCEDHXIYrO4ahBScvYNL8fia-ZkcTrVWtAOXxrhF2AoRj723sGKPrf9nOcJzgWvNpAyMRXYV8FLkhBYFdK0CUFNKZ1DZGi-PfXp0PRdih-UTWkFB9IBI1EABWwcWEX9dbHowZ41ZfFqgIHd8Xw7gqjOzuXcQx1Hd4zLcad_7WFO2j5GYX4G5CQP9DvafM78zEsWJuYY45WKf5kq_RI1QDMLFnUkRFnev12MyNll3Dw0RlTp6C2n8-jFLBPHfrSzwVh0nGFn85tpuShGJcKlUf0x0vIdkAdj_g-oViV97Ddvfr7vHXk9Ct9Un8CpMdimuAUhADFtJ-6Q0DDW42OKhT8TnRdBImCz0-2TT3lbO1Oni_mRVxt4_YEu4B3fxOwTVqYqPDpUxm879mQWQy9okQrxFQOgKpXlUfkcX9AXQuGKdyalKm3WFn3hiqyP_kKWH77N2QadWSXYWZAXDgC4pwXCKbXI738w3lKMIlpxTWjdNdb_P1v7qZ6Wm60nbMeLSQ_itVYND1yBI2LXq6gMK4ENWxaqnYTzRxNwiuGOcVjk5fesHJk6XSiUf9FIzCzt5Qc4uuWHXKwd8QusXmbR3o41va6-Fy0hjPMXOhIntQdhetxn7zz1MRn0JV9mJnibruOM04OQBlcZ8dFL-j_kHarEd03FnQMrX0AgOpRENVg9DJcGRE3T0MV0nGISIePhoABNkLUCdy-NHCqN2zRGM2iT2bVTPkJ3XeCIK40WOlmNS6VcjOJD37L5yY7CclspuQ-c_CN5ZZnTjfzpNBvtqYqo4mBGBW_T_DR05NtoZzO-RCO_2CRCrCCsdaVEGvvT99GhAuHoAQPK1G1ilWjrZvX95xL8rzwCGyYBnLOV6Cii1c0X3KDAZKE3U4IVfY4UCRFtUCfQTqecpP8DutOF7qv_JTjRBP9cUrIlZY58rZODiR7GMytdmUBOYinQz7i0U0Quub9X1cLkGrPs2JKLOASM_heZMbBZ2uV3Y_YzMDekHZcbPdAS0m-fjjrvHNi9MpeDF3U_TJ4eQDICrmvjImgkKpOveWeo9iv4amNjvwUtclC7HyugA8bGozier-TN3EbGCd_c_G0ImdxY_xUGmLM_cBR0E8GmemXSuRbuXuUizf1cUsLczGWds5ySXZ9kFQoiCBHBKLqQP9s4RQ5Ob4Q_kO8CFh4LmbeW5DUyP2ru8Adm2XdN7HgMnz8aBh5juoxjqjZEXduM9Xip_zH67Izbq4hWGOFbBW7g47CpnPVTXiS-jaj3aJDtQt6RPlhltYJemUmiX4GPGpMUFJo0SRkyMowTHrzVBNSkfp5XlDfOcV6n-3uILb_jZ3vK6MMUEs3HKdhNaqrk3nGVPF3FDuDFIC7DVzXAXyRpT5epr_RYcGjlDm7PYsTNqAIwH03ocU4yrDsY6p4H95coptWZbVykxr0V3KyEvcqQ0sAjlMysaNB2iZha71p7KvgrOp3kimvw0ec7HYtFFtCxIb6e-_uKHgdXQ7yTKnX8iwMbLlUoYwGrLeWouRuQq8c5wzOXeRliv0G5YsxFSzTuaX4hVQrUKewcWsDD8yg8cWQ_UkaUU8Uci6Bx3AbyIPyentUYXIFJTYWsZBIKaSJ7xBtCJRRgh_UzSI3T3uOx4h_KGq40d73H7nh8nClj2d9dSUz1jtxJxx1MPaIpPkhOdt-wqWh_YDCOprit6f8xlYALIEheWTfLRKzIt4jZJOjPGF8vYymLA4wW9Wj39Vq1MWlVUoGx-X_Y94S7mpuFXY85WwlI72wWfwhvx_YkamKcZVxojtePXCXG-jsqY_s66yZnK-ty3TrLY2zQ8pPzExBAI8JFp3wP1IwGp8rnxyu8Xm4PnSPigPFKpjxICjNQC7mBbP1AyxuZ1fXugS7Qq5ab-nw3s3Uy7pNeUmy9qhdISVUuWtZiAelExVqNMDtJz2wOJMD4CAEXX4uwjXhuq1DGVxiCROiJ0qUJigLjmpK-jAKiui-pNgaBEur6TJ3t9LeSkrOQJooxBFa9ehMUcdpHsOlv7AYWivcVqbaowsX9QH7sy0wBmji6dmHV0lZP_FcWv6x3JKNde-KDz-OXIIuF0MmvM2GcWiQjEMGyDo1zYyRa8bNQBzUyQN767g9GDWpsEq40ASMXRkflUqqfvErUnAz2Jm9Qt_Ze5O1gHe-v3DL6C068Dz74eh-8TlQ-rJj4jUTG-Peq7rGRDs8QVNZMDkshrwnOJGvQiTeoQCL0-LJuLX6FqMUdeskFYZu6NL8avwMQWEMHT67XzDZzA.8d7iPhiZRj5W0r7MFN3Kyg";
    public static final String CF_CLEARANCE = "ohnNk6VaskpOD.Rkdj94nal8_6iTOKqgyyKhAoofZwo-1677651717-0-1-dbcb6581.bbac1e0d.8cca3a61-160";
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