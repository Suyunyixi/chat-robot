package club.suyunyixi.robot.infrastructure.register;

import club.suyunyixi.robot.infrastructure.utils.ApplicationUtil;
import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 机器人后台定时线程，每 3 分钟执行一次，用户可向此类提交后台任务
 */
@Slf4j
@Component
public class RobotJobRegister {
    @Resource
    private ApplicationUtil applicationUtil;
    /**
     * 后台线程执行的任务列表
     */
    private static final List<Runnable> TASKS = new CopyOnWriteArrayList<>();

    /**
     * 需要动态刷新
     */
    @SneakyThrows
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void init() {
        // 初始化任务
        rmAll();
        addCronTask(applicationUtil.tasks());
        log.info("刷新task, num: {}", TASKS.size());
    }

    @Scheduled(cron = "0 0/3 * * * ? ")
    public void run() {
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

    public static void rmAll() {
        TASKS.removeAll(TASKS);
    }
}