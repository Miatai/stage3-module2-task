package com.mjc.school.controller.commands.implementation.news;

import static com.mjc.school.controller.utils.Constant.NEWS_ID;
import static com.mjc.school.controller.utils.Constant.NEWS_ID_ENTER;
import static com.mjc.school.controller.utils.Utils.getKeyboardNumber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.Commands;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Component
public class ReadNewsByIdCommand implements Command {
    private static final Commands command = Commands.GET_NEWS_BY_ID;
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    @Autowired
    public ReadNewsByIdCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute() {
        Method[] methods = newsController.getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(CommandHandler.class) != null
                    && command.getCommandNumber().equals(method.getAnnotation(CommandHandler.class).commandNumber())) {
                System.out.println(command.getCommand());
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    if (parameter.getAnnotation(CommandParam.class) != null
                            && NEWS_ID.equals(parameter.getAnnotation(CommandParam.class).value())) {
                        System.out.println(NEWS_ID_ENTER);
                        Long id = getKeyboardNumber(NEWS_ID);
                        
                            NewsDtoResponse newsDtoResponce;
                            try {
                                newsDtoResponce = (NewsDtoResponse) method.invoke(newsController,
                                        id);
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
