package com.bupt.trainbookingsystem.annotation;


import com.bupt.trainbookingsystem.enums.OperationType;
import com.bupt.trainbookingsystem.enums.OperationUnit;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {

    String Detail() default "";

    int level() default 0;

    OperationType operationType() default OperationType.UNKNOWN;

    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}
