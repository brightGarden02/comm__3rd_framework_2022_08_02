package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@AllArgsConstructor
public class RouteInfo {

    private String path;
    private String actionPath;

    @Getter
    private Class controllerCls;
    @Getter
    private Method method;
}
