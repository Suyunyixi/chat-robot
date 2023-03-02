package club.suyunyixi.robot.infrastructure.anno;


import club.suyunyixi.robot.domain.command.chain.BaseChain;
import club.suyunyixi.robot.domain.entity.enums.MessageSource;

import java.lang.annotation.*;

/**
 * 责任链注册注解
 *
 * @author Suyunyixi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChainService {
    /**
     * 属于哪个分支
     */
    ChainLine[] lines();

    @interface ChainLine {

        /**
         * 属于哪个分支
         */
        MessageSource branch();

        /**
         * 该分支下的父级, 不写则代表父级
         */
        String parent() default BaseChain.ORIGIN_MAIN_KEY;

        /**
         * 注册名
         */
        String name();
    }
}