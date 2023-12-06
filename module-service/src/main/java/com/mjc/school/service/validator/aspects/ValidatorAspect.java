package com.mjc.school.service.validator.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.validator.Validator;

@Aspect
@Component
public class ValidatorAspect {
    private Validator validator;

    @Autowired
    public ValidatorAspect(Validator validator) {
        this.validator = validator;
    }

    @Before("@annotation(ValidateNews) && args(newsDtoRequest)")
    public void validateNewsRequest(NewsDtoRequest newsDtoRequest) {
        validator.validateNewsDtoRequest(newsDtoRequest);
    }

    @Before("@annotation(ValidateAuthor) && args(authorDtoRequest)")
    public void validateAuthorRequest(AuthorDtoRequest authorDtoRequest) {
        validator.validateAuthorDtoRequest(authorDtoRequest);
    }

    @Before("@annotation(ValidateNewsId) && args(newsId)")
    public void validteNewsId(Long newsId) {
        validator.validateNewsId(newsId);
    }

    @Before("@annotation(ValidateAuthorId) && args(authorId)")
    public void validteAuthorId(Long authorId) {
        validator.validateAuthorId(authorId);
    }
}
