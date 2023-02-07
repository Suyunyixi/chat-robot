package club.suyunyixi.robot.infrastructure.register;

import club.suyunyixi.robot.domain.command.BaseChain;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;
import club.suyunyixi.robot.infrastructure.anno.ChainService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Suyunyixi
 * @date 2022/9/23 18:37
 **/
@Slf4j
@Configuration
public class ChainRegister {
    @Resource
    private ApplicationContext applicationContext;

    public static ConcurrentMap<MessageSource, BaseChain<?, ?, ?>> MAIN;

    @PostConstruct
    public void init() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ChainService.class);
        if (CollUtil.isEmpty(MAIN)) {
            MAIN = new ConcurrentHashMap<>(1 << (int) (MessageSource.values().length * 0.75 / 2));
        }
        // 归类, 注册
        register(categorize(beans));
    }

    /**
     * 将各分类责任链, 分级注册
     */
    private void register(Map<MessageSource, Map<String, Map<String, BaseChain<?, ?, ?>>>> map) {
        map.forEach((k1, v1) -> {
            Map<String, BaseChain<?, ?, ?>> v2 = v1.get(BaseChain.ORIGIN_MAIN_KEY);
            // 仅有一个主入口, 否则不认
            if (CollUtil.isNotEmpty(v2) && v2.size() == 1) {
                // 当前是main
                BaseChain<?, ?, ?> mainChain = v2.get(BaseChain.MAIN_CHAIN_KEY);
                // 存在main
                if (mainChain != null) {
                    MAIN.put(k1, mainChain);
                    // 在当前BranchEnum分类下, 获取指定key的chain添加到父级chain中, 并递归获取
                    finChild(k1, v1, BaseChain.MAIN_CHAIN_KEY, mainChain);
                }
            }
        });
    }

    /**
     * 在当前BranchEnum分类下, 获取指定key的chain添加到父级chain中, 并递归获取
     */
    private void finChild(MessageSource type,
                          Map<String, Map<String, BaseChain<?, ?, ?>>> all,
                          String key,
                          BaseChain<?, ?, ?> thisChain) {
        Optional.ofNullable(all)
                .orElse(new HashMap<>())
                .getOrDefault(key, new HashMap<>())
                .forEach((k, v) -> {
                    thisChain.addNextChain(k, v, type);
                    finChild(type, all, k, v);
                });
    }


    /**
     * 将所有ChainService修饰的BaseChain实现类归类好
     */
    private Map<MessageSource, Map<String, Map<String, BaseChain<?, ?, ?>>>> categorize(Map<String, Object> beans) {
        Map<MessageSource, Map<String, Map<String, BaseChain<?, ?, ?>>>> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof BaseChain) {
                // 依次获取到 使用了该注解的对象
                Class<?> aClass = AopUtils.getTargetClass(value);
                // 获取当前对象 声明的注解 获取到注解后 还可以获取注解中的属性
                ChainService chain = aClass.getDeclaredAnnotation(ChainService.class);
                for (ChainService.ChainLine line : chain.lines()) {
                    add(map, line, (BaseChain<?, ?, ?>) value);
                }
            }
        }
        return map;
    }

    /**
     * 只负责添加
     */
    private void add(Map<MessageSource, Map<String, Map<String, BaseChain<?, ?, ?>>>> map,
                     ChainService.ChainLine lint,
                     BaseChain<?, ?, ?> value) {
        MessageSource k1 = lint.branch();
        String k2 = lint.parent();
        String k3 = lint.name();
        // 存在k1
        if (map.containsKey(k1)) {
            Map<String, Map<String, BaseChain<?, ?, ?>>> v = map.get(k1);
            // 存在k2
            if (v.containsKey(k2)) {
                // 添加k3
                v.get(k2).put(k3, value);
            } else {
                // 添加k2
                v.put(k2, m3(k3, value));
            }
        } else {
            // 添加k1
            map.put(k1, m2(k2, m3(k3, value)));
        }
    }

    /**
     * level 3 map
     */
    private Map<String, BaseChain<?, ?, ?>> m3(String k, BaseChain<?, ?, ?> v) {
        Map<String, BaseChain<?, ?, ?>> m3 = new HashMap<>();
        m3.put(k, v);
        return m3;
    }

    /**
     * level 2 map
     */
    private Map<String, Map<String, BaseChain<?, ?, ?>>> m2(String k, Map<String, BaseChain<?, ?, ?>> m3) {
        Map<String, Map<String, BaseChain<?, ?, ?>>> m2 = new HashMap<>();
        m2.put(k, m3);
        return m2;
    }
}
