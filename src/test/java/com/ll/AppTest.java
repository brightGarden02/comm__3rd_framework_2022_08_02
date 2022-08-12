package com.ll;

import com.ll.exam.Container;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {

    @Test
    public void junit_assertThat() {

        int rs = 10 + 20;

        assertThat(rs).isEqualTo(30);
    }


    @Test
    public void ioc__articleController() {
        ArticleController articleController = (ArticleController) Container.getObj(ArticleController.class);

        assertThat(articleController).isNotNull();
    }


    @Test
    public void ioc__articleContoller__싱글톤() {
        ArticleController articleController1 = (ArticleController) Container.getObj(ArticleController.class);
        ArticleController articleController2 = (ArticleController) Container.getObj(ArticleController.class);


        assertThat(articleController2).isEqualTo(articleController1);
    }

    @Test
    public void ioc__homeContoller() {
        HomeController homeController = (HomeController) Container.getObj(HomeController.class);

        assertThat(homeController).isNotNull();
    }


    @Test
    public void ioc__homeController__싱글톤() {
        HomeController homeController1 = (HomeController) Container.getObj(HomeController.class);
        HomeController homeController2 = (HomeController) Container.getObj(HomeController.class);

        assertThat(homeController2).isEqualTo(homeController1);
    }


//    @Test
//    public void ioc__Controller들을_스캔하여_수집() {
//        List<String> names = Container.getControllerNames();
//
//        assertThat(names).contains("home");
//        assertThat(names).contains("article");
//
//    }

}
