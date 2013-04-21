package oliver.test.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * repeat method invocation
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-04-21 下午11:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Repeat {
    int value() default 1;
}
