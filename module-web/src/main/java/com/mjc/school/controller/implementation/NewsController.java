package com.mjc.school.controller.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Controller("newsController")
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService){
        this.newsService = newsService;
    }

    @CommandHandler(commandNumber = 1)
    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @CommandHandler(commandNumber = 3)
    @Override
    public NewsDtoResponse readById(@CommandParam("News Id") Long newsId) {
        return newsService.readById(newsId);
    }

    @CommandHandler(commandNumber = 5)
    @Override
    public NewsDtoResponse create(@CommandBody NewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }

    @CommandHandler(commandNumber = 7)
    @Override
    public NewsDtoResponse update(@CommandBody NewsDtoRequest dtoRequest) {
        return newsService.update(dtoRequest);
    }

    @CommandHandler(commandNumber = 9)
    @Override
    public boolean deleteById(@CommandParam("newsId") Long newsId) {
        return newsService.deleteById(newsId);
    }
}
