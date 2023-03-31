package club.suyunyixi.robot.infrastructure.register;

import club.suyunyixi.robot.infrastructure.utils.ApplicationUtil;
import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 机器人后台定时线程，每 3 分钟执行一次，用户可向此类提交后台任务
 * 监听任务注册
 *
 * @author Suyunyixi
 */
@Slf4j
@Component
public class RobotJobRegister implements ApplicationRunner {
    @Resource
    private ApplicationUtil applicationUtil;
    /**
     * 后台线程执行的任务列表
     */
    private static final List<Runnable> TASKS = new CopyOnWriteArrayList<>();

    @Scheduled(cron = "0 0/3 * * * ? ")
    public void runTask() {
        // 任务启动
        for (Runnable task : TASKS) {
            task.run();
        }
    }

    /**
     * 添加机器人定时后台任务
     *
     * @param task
     */
    public static void addCronTask(Runnable task) {
        TASKS.add(task);
    }

    public static void addCronTask(List<? extends Runnable> tasks) {
        if (CollUtil.isNotEmpty(tasks)) {
            TASKS.addAll(tasks);
        }
    }

    public static void rmCronTask(Runnable task) {
        TASKS.remove(task);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化任务
        addCronTask(applicationUtil.tasks());
        log.info("注册task, num: {}", TASKS.size());
    }
}