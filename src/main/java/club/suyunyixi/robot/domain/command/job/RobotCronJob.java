package club.suyunyixi.robot.domain.command.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 机器人后台定时线程，每 3 分钟执行一次，用户可向此类提交后台任务
 * <p><strong>机器人应该只有一个 {@link org.quartz.Scheduler}，若想要自定义定时任务，可调用此类的 {@link #submitCronJob(Class, ScheduleBuilder, JobDataMap)} 方法</strong></p>
 *
 * @author Happysnaker
 * @description
 * @date 2022/7/2
 * @email happysnaker@foxmail.com
 */
@Slf4j
@Component
public class RobotCronJob implements Job {
    /**
     * 后台线程执行的任务列表
     */
    public static List<Runnable> TASKS = new CopyOnWriteArrayList<>();
    /**
     * 定时调度器
     *
     * @since v3.3
     */
    public static Scheduler SCHEDULER;
    public static final int PERIOD_MINUTE = 3;

    static {
        try {
            SCHEDULER = StdSchedulerFactory.getDefaultScheduler();
            // 涉及到时间调度，默认使用中国标准时间
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SCHEDULER.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交一个通用的、自定义触发时间的定时任务
     *
     * @param c               Class<? extends Job>
     * @param scheduleBuilder 调度规则
     * @param data            传递的数据
     * @throws SchedulerException
     */
    public static void submitCronJob(Class<? extends Job> c, ScheduleBuilder<? extends Trigger> scheduleBuilder, JobDataMap data) throws SchedulerException {
        if (data == null) {
            data = new JobDataMap();
        }
        JobDetail jobDetail = JobBuilder.newJob(c)
                .usingJobData(data)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(scheduleBuilder)
                .build();
        SCHEDULER.scheduleJob(jobDetail, trigger);
        log.info("提交任务 {}，下一次执行时间 {}", jobDetail.getKey(), trigger.getNextFireTime());
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
        RobotCronJob.TASKS.addAll(tasks);
    }

    public static void rmCronTask(Runnable task) {
        TASKS.remove(task);
    }

    /**
     * 机器人定时后台任务
     */
    @Override
    public void execute(JobExecutionContext context) {
        try {
            // 是否启用robot
            log.info(new Date() + ", run robot cron job...");
            for (Runnable task : TASKS) {
                task.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
