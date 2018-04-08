package me.frank.manager.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by frank on 2017/8/22.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Regx {

    String type();

    String name();
}
