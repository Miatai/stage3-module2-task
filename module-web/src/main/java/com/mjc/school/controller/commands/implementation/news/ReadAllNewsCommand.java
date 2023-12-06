package com.mjc.school.controller.commands.implementation.news;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Component
public class ReadAllNewsCommand implements Command {
    private static final Commands command = Commands.GET_ALL_NEWS;
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    @Autowired
    public ReadAllNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute() {
        Method[] methods = newsController.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CommandHandler.class) !=null && command.getCommandNumber().equals(method.getAnnotation(CommandHandler.class).commandNumber())) {
                System.out.println(command.getCommand());
                try {
                    List<NewsDtoResponse> list = (List<NewsDtoResponse>) method.invoke(newsController);
                    list.forEach(System.out::println);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException(e.getCause().getMessage());
                }
                break;
            }
        }
    }
}
