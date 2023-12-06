package com.mjc.school.controller.commands.implementation.news;

import static com.mjc.school.controller.utils.Constant.NEWS_TITLE_ENTER;
import static com.mjc.school.controller.utils.Utils.getKeyboardNumber;
import static com.mjc.school.controller.utils.Utils.getKeyboardString;
import static com.mjc.school.controller.utils.Constant.NEWS_CONTENT_ENTER;
import static com.mjc.school.controller.utils.Constant.AUTHOR_ID_ENTER;
import static com.mjc.school.controller.utils.Constant.AUTHOR_ID;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Component
public class CreateNewsCommand implements Command {
    private static final Commands command = Commands.CREATE_NEWS;
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    @Autowired
    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute() {
        Method[] methods = newsController.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CommandHandler.class) !=null && command.getCommandNumber().equals(method.getAnnotation(CommandHandler.class).commandNumber())) {
                System.out.println(command.getCommand());
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    if (parameter.isAnnotationPresent(CommandBody.class)) {
                        System.out.println(NEWS_TITLE_ENTER);
                        String title = getKeyboardString();
                        System.out.println(NEWS_CONTENT_ENTER);
                        String content = getKeyboardString();
                        System.out.println(AUTHOR_ID_ENTER);
                        Long authorId = getKeyboardNumber(AUTHOR_ID);
                        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(null, title, content, authorId);
                        try {
                            NewsDtoResponse newsDtoResponce = (NewsDtoResponse) method.invoke(newsController,
                                    newsDtoRequest);
                            System.out.println(newsDtoResponce);

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
