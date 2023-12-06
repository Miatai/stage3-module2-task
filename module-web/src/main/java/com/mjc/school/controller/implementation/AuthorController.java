package com.mjc.school.controller.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

@Controller("authorController")
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @CommandHandler(commandNumber = 2)
    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @CommandHandler(commandNumber = 4)
    @Override
    public AuthorDtoResponse readById(@CommandParam("Author Id") Long authorId) {
        return authorService.readById(authorId);
    }

    @CommandHandler(commandNumber = 6)
    @Override
    public AuthorDtoResponse create(@CommandBody AuthorDtoRequest dtoRequest) {
        return authorService.create(dtoRequest);
    }

    @CommandHandler(commandNumber = 8)
    @Override
    public AuthorDtoResponse update(@CommandBody AuthorDtoRequest dtoRequest) {
        return authorService.update(dtoRequest);
    }

    @CommandHandler(commandNumber = 10)
    @Override
    public boolean deleteById(@CommandParam("authorId") Long authorId) {
        return authorService.deleteById(authorId);
    }

}
