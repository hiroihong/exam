package com.example.demo.common.web;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {
    boolean loginCheck() default false;
}
