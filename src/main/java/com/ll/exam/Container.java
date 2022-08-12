package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

import java.util.List;

public class Container {

    private static final ArticleController articleController;

    static {
        articleController = new ArticleController();
    }


    public static ArticleController getArticleController() {
        return articleController;
    }

    public static List<String> getControllerNames(){
        return List.of("home", "article");
    }
}
