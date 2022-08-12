package com.ll.exam;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@AllArgsConstructor
public class RouteInfo {

    private String path;
    private Method method;
}
