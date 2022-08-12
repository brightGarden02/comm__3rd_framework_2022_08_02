package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerManager {

    private static Map<String, RouteInfo> routeInfos;

    static {
        routeInfos = new HashMap<>();

        scanMappings();
    }

    private static void scanMappings() {

        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for(Class<?> cl : ref.getTypesAnnotatedWith(Controller.class)) {
            Method[] methods = cl.getDeclaredMethods();

            for(Method method : methods) {

                GetMapping getMapping = method.getAnnotation(GetMapping.class);

                String httpMethod = null;
                String path = null;

                if(getMapping != null) {
                    path = getMapping.value();
                    httpMethod = "GET";
                }

                if(path != null && httpMethod != null) {
                    String key = httpMethod + "___" + path;

                    routeInfos.put(key, new RouteInfo(path, method));
                }
            }
        }

    }

    public static void runAction(HttpServletRequest request, HttpServletResponse response) {

    }

    public static void init() {

    }

    public static Map<String, RouteInfo> getRouteInfosForTest() {
        return routeInfos;
    }

}
