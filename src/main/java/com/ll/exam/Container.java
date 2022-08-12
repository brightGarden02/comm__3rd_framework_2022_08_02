package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Container {

    private static final ArticleController articleController;
    private static final HomeController homeController;


    static {
        articleController = Ut.cls.newObj(ArticleController.class, null);
        homeController = Ut.cls.newObj(HomeController.class, null);
    }


    public static ArticleController getArticleController() {
        return articleController;
    }

    public static HomeController getHomeController() {
        return homeController;
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
