package club.suyunyixi.robot.domain.command.handler;

import club.suyunyixi.robot.domain.entity.base.BaseContext;
import club.suyunyixi.robot.domain.entity.base.BaseParam;
import club.suyunyixi.robot.domain.entity.base.BaseRespMessage;
import club.suyunyixi.robot.infrastructure.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象时间处理器
 *
 * @author Suyunyixi
 * @date 2023/2/7 11:45
 */
@Slf4j
public abstract class AbstractHandler
        implements CommandHandler, CommandHandlerManager {
    /**
     * 对外执行调用就行, 外层只需要调用这个方法
     * Created by Suyunyixi on 2023/2/11 19:49
     */
    public BaseRespMessage execute(BaseParam param, BaseContext data) {
        try {
            // 获取req content
            data.setReqContent(StrUtil.reqContent(param.getReqMessage(), data.getHandler()));
            // 解析执行
            BaseRespMessage resp = explain(param, data);
            // 执行成功的示例
            success(resp);
            return resp;
        } catch (Exception e) {
            log.error("error cause by", e);
            // 如果失败
            return fail(data);
        }
    }
}