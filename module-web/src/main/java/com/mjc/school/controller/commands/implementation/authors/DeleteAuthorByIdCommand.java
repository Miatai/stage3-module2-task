package com.mjc.school.controller.commands.implementation.authors;

import static com.mjc.school.controller.utils.Constant.AUTHOR_ID;
import static com.mjc.school.controller.utils.Constant.AUTHOR_ID_ENTER;
import static com.mjc.school.controller.utils.Utils.getKeyboardNumber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

@Component
public class DeleteAuthorByIdCommand implements Command {
    private static final Commands command = Commands.REMOVE_AUTHOR_BY_ID;
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public DeleteAuthorByIdCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute() {
        Method[] methods = authorController.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CommandHandler.class) !=null && command.getCommandNumber().equals(method.getAnnotation(CommandHandler.class).commandNumber())) {
                System.out.println(command.getCommand());
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    if (parameter.isAnnotationPresent(CommandParam.class)) {
                        System.out.println(AUTHOR_ID_ENTER);
                        Long id = getKeyboardNumber(AUTHOR_ID);
                        try {
                            System.out.println(method.invoke(authorController, id));
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            throw new RuntimeException(e.getCause().getMessage());
                        }
                    }
                }
                break;
            }
        }
    }
}
