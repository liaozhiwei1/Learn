package MySpring.Spring;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:36:10
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    String value() default "";

}
