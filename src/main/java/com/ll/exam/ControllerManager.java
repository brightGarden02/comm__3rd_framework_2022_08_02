package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.util.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
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
        for(Class<?> controllerCls : ref.getTypesAnnotatedWith(Controller.class)) {
            Method[] methods = controllerCls.getDeclaredMethods();

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

                    routeInfos.put(key, new RouteInfo(path, actionPath, controllerCls, method));
                }
            }
        }

    }


    public static void runAction(HttpServletRequest request, HttpServletResponse response) {
        Rq rq = new Rq(request, response);

        String routeMethod = rq.getRouteMethod();
        String actionPath = rq.getActionPath();

        String mappingKey = routeMethod + "___" + actionPath;


        boolean contains = routeInfos.containsKey(mappingKey);

        if (contains == false) {
            rq.println("해당 요청은 존재하지 않습니다.");
            return;
        }

        runAction(rq, routeInfos.get(mappingKey));

    }

    private static void runAction(Rq rq, RouteInfo routeInfo) {
        Class controllerCls = routeInfo.getControllerCls();
        Method actionMethod = routeInfo.getMethod();

        Object controllerObj = Container.getObj(controllerCls);

        try {
            actionMethod.invoke(controllerObj, rq);
        } catch (IllegalAccessException e) {
            rq.println("액션시작에 실패하였습니다.");
        } catch (InvocationTargetException e) {
            rq.println("액션시작에 실패하였습니다.");
        }
    }

    public static void init() {

    }

    public static Map<String, RouteInfo> getRouteInfosForTest() {
        return routeInfos;
    }

}
