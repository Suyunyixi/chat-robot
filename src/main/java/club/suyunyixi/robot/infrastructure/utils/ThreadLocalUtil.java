package club.suyunyixi.robot.infrastructure.utils;

import org.springframework.stereotype.Component;

/**
 * @author Suyunyixi
 */
@Component
public class ThreadLocalUtil<T> {

    private final ThreadLocal<T> threadLocal = new ThreadLocal<>();

    public void set(T str) {
        threadLocal.set(str);
    }

    public T get() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}