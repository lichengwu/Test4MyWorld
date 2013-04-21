package oliver.test.common;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * run test method with log
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-04-21 下午10:31
 */
public class DefaultLoggerTestRunner extends BlockJUnit4ClassRunner {
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @throws org.junit.runners.model.InitializationError
     *          if the test class is malformed.
     */
    public DefaultLoggerTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        long begin = System.currentTimeMillis();
        Repeat annotation = method.getMethod().getAnnotation(Repeat.class);
        int repeat = 1;
        if (annotation != null) {
            repeat = annotation.value();
        }
        for (int i = 0; i < repeat; i++) {
            super.runChild(method, notifier);
        }
        System.out.println(method.getName() + ", avg time used " + (System.currentTimeMillis() - begin) / repeat + "ms");
    }
}
