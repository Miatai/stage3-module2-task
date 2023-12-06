package com.mjc.school.controller.commands.implementation.authors;

import static com.mjc.school.controller.utils.Constant.AUTHOR_ID;
import static com.mjc.school.controller.utils.Constant.AUTHOR_ID_ENTER;
import static com.mjc.school.controller.utils.Constant.AUTHOR_NAME_ENTER;
import static com.mjc.school.controller.utils.Utils.getKeyboardNumber;
import static com.mjc.school.controller.utils.Utils.getKeyboardString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

@Component
public class UpdateAuthorCommand implements Command {
    private static final Commands command = Commands.UPDATE_AUTHOR;
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    public UpdateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
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
                    if (parameter.isAnnotationPresent(CommandBody.class)) {
                        System.out.println(AUTHOR_ID_ENTER);
                        Long id = getKeyboardNumber(AUTHOR_ID);
                        System.out.println(AUTHOR_NAME_ENTER);
                        String name = getKeyboardString();
                        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest(id, name);
                        try {
                            AuthorDtoResponse authorDtoResponce = (AuthorDtoResponse) method.invoke(authorController,
                                    authorDtoRequest);
                            System.out.println(authorDtoResponce);
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
