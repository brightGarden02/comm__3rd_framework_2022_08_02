package com.ll.exam.article.service;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Service;
import com.ll.exam.article.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
}
