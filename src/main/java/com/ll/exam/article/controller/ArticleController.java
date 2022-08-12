package com.ll.exam.article.controller;


import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;

@Controller // ArticleController가 컨트롤러이다.
public class ArticleController {


    // /usr/article/list/free 와 같이 관련된 요청을 처리하는 함수이다.
    @GetMapping("/usr/article/list")
    // 아래 showList 는 Get /usr/article/list 으로 요청이 왔을 때 실행 되어야 하는 함수
    public void showList(){

    }

}
