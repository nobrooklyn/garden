package local.garden.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextHolder {
    private static final Logger log = LoggerFactory.getLogger(ContextHolder.class);
    private static ThreadLocal<User> context = new InheritableThreadLocal<>() {
        @Override
        protected User childValue(User parent) {
            return parent.clone();
        }
    };

    public static void set(User u) {
        log.info("set context: " + u);
        context.set(u);
    }

    public static User get() {
        User u = context.get();
        log.info("get context: " + u);
        return u;
    }

    public static void remove() {
        context.remove();
    }
}