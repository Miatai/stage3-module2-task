package com.mjc.school.controller.commands.implementation.authors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

@Component
public class ReadAllAuthorsCommand implements Command {
    private static final Commands command = Commands.GET_ALL_AUTHORS;
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public ReadAllAuthorsCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute() {
        Method[] methods = authorController.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CommandHandler.class) !=null && command.getCommandNumber().equals(method.getAnnotation(CommandHandler.class).commandNumber())) {
                System.out.println(command.getCommand());
                try {
                    List<AuthorDtoResponse> list = (List<AuthorDtoResponse>) method.invoke(authorController);
                    list.forEach(System.out::println);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException(e.getCause().getMessage());
                }
                break;
            }
        }
    }
}
