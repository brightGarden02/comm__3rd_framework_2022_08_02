package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.util.Ut;
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
                    String actionPath = Ut.str.beforeFrom(path, "/", 4);

                    String key = httpMethod + "___" + actionPath;

                    routeInfos.put(key, new RouteInfo(path, actionPath, method));
                }
            }
        }

    }


    public static void runAction(HttpServletRequest request, HttpServletResponse response) {
        Rq rq = new Rq(request, response);

        String routeMethod = rq.getRouteMethod();
        String actionPath = rq.getActionPath();

        String mappingKey = routeMethod + "___" + actionPath;

        System.out.println(mappingKey);
        System.out.println(routeInfos.keySet());

        boolean contains = routeInfos.containsKey(mappingKey);

        if (contains == false) {
            rq.println("해당 요청은 존재하지 않습니다.");
            return;
        }

        rq.println("해당 요청은 존재합니다.");

    }

    public static void init() {

    }

    public static Map<String, RouteInfo> getRouteInfosForTest() {
        return routeInfos;
    }

}
