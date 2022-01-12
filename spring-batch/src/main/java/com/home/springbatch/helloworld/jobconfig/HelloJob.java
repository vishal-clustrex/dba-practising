package com.home.springbatch.helloworld.jobconfig;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Inherited()
@Qualifier("HelloJob")
public @interface HelloJob {

}
