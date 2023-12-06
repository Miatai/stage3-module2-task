package com.mjc.school.repository.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;

@Aspect
@Component
public class OnDeleteAspect {
    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public OnDeleteAspect(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Pointcut("@annotation(OnDelete) && args(authorId)")
    public void deleteAuthorPoinCut(Long authorId) {

    }

    @Before("deleteAuthorPoinCut(authorId)")
    public void afterDeleteAuthor(Long authorId) {
        newsRepository.readAll().removeIf(x -> x.getAuthorId().equals(authorId));
    }
}
