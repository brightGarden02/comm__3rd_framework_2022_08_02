package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {

    private static Map<Class, Object> objects;


    static {
        objects = new HashMap<>();
        objects.put(ArticleController.class, new ArticleController());
        objects.put(HomeController.class, new HomeController());
    }

    public static <T> T getObj(Class<T> cls) {
        return (T) objects.get(cls);
    }


    public static List<String> getControllerNames(){

        List<String> names = new ArrayList<>();

        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            String name = cls.getSimpleName(); // HomeController
            name = name.replace("Controller", ""); // Home
            name = Ut.str.decapitalize(name); // Home

            names.add(name.replace("Controlller", name));
        }

        return names;
    }
}
