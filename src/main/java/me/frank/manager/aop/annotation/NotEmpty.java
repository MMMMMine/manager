package me.frank.manager.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by frank on 2017/8/14.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotEmpty {

    String name() default "fieldName";
}
