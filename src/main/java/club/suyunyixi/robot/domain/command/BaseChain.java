package club.suyunyixi.robot.domain.command;

import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.error.BizError;
import club.suyunyixi.robot.infrastructure.register.ChainRegister;
import club.suyunyixi.robot.infrastructure.utils.ExceptionUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理链
 *
 * @param <P> 公共参数
 * @param <D> 传输DTO
 * @param <R> 结果
 * @author Suyunyixi
 */
@Slf4j
public abstract class BaseChain<P, D, R> {
    protected Map<MessageSource, Map<Object, BaseChain<P, D, R>>> nextChainMap;

    /**
     * 入口调用链key
     */
    public static final String MAIN_CHAIN_KEY = "main";
    public static final String ORIGIN_MAIN_KEY = "ORIGIN_MAIN_KEY";

    /**
     * 链调用方法
     */
    public abstract R handle(P params, D data);

    protected BaseChain<P, D, R> nextChain(Object nextKey, MessageSource type) {
        log.info("to next chain: {}, nextKey: {}", type, nextKey);
        ExceptionUtil.throwIfTrue(CollUtil.isEmpty(nextChainMap), BizError.CHAIN_IS_VULNERABLE);
        BaseChain<P, D, R> chain = nextChainMap.get(type).get(nextKey);
        ExceptionUtil.throwIfTrue(ObjectUtil.isNull(chain), BizError.CHAIN_IS_VULNERABLE);
        return chain;
    }

    @SuppressWarnings("unchecked")
    public void addNextChain(Object key, BaseChain<?, ?, ?> handelChain, MessageSource type) {
        if (Objects.isNull(nextChainMap)) {
            nextChainMap = new ConcurrentHashMap<>(4);
        }
        Map<Object, BaseChain<P, D, R>> map = nextChainMap.get(type);
        if (CollUtil.isEmpty(map)) {
            map = new ConcurrentHashMap<>();
        }
        map.put(key, (BaseChain<P, D, R>) handelChain);
        nextChainMap.put(type, map);
    }

    @SuppressWarnings("unchecked")
    protected BaseChain<P, D, R> findMain(MessageSource type) {
        BaseChain<?, ?, ?> chain = ChainRegister.MAIN.get(type);
        if (ObjectUtil.isEmpty(chain)) {
            throw new IllegalArgumentException("find illegal");
        }
        return (BaseChain<P, D, R>) chain;
    }

    protected R start(P p, D d, MessageSource type) {
        return findMain(type).handle(p, d);
    }
}