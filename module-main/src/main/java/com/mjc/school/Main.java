package com.mjc.school;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mjc.school.controller.commands.Invoker;
import com.mjc.school.controller.commands.implementation.authors.CreateAuthorCommand;
import com.mjc.school.controller.commands.implementation.authors.DeleteAuthorByIdCommand;
import com.mjc.school.controller.commands.implementation.authors.ReadAllAuthorsCommand;
import com.mjc.school.controller.commands.implementation.authors.ReadAuthorByIdCommand;
import com.mjc.school.controller.commands.implementation.authors.UpdateAuthorCommand;
import com.mjc.school.controller.commands.implementation.news.CreateNewsCommand;
import com.mjc.school.controller.commands.implementation.news.DeleteNewsByIdCommand;
import com.mjc.school.controller.commands.implementation.news.ReadAllNewsCommand;
import com.mjc.school.controller.commands.implementation.news.ReadNewsByIdCommand;
import com.mjc.school.controller.commands.implementation.news.UpdateNewsCommand;
import com.mjc.school.helper.MenuHelper;

public class Main {
  public static final String COMMAND_NOT_FOUND = "Command not found.";
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    MenuHelper helper = new MenuHelper();
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    Invoker invoker = context.getBean(Invoker.class);
    while (true) {
      try {
        helper.printMainMenu();
        String key = keyboard.nextLine();
        switch (key) {
          case "1" -> invoker.setCommand(context.getBean(ReadAllNewsCommand.class));
          case "2" -> invoker.setCommand(context.getBean(ReadAllAuthorsCommand.class));
          case "3" -> invoker.setCommand(context.getBean(ReadNewsByIdCommand.class));
          case "4" -> invoker.setCommand(context.getBean(ReadAuthorByIdCommand.class));
          case "5" -> invoker.setCommand(context.getBean(CreateNewsCommand.class));
          case "6" -> invoker.setCommand(context.getBean(CreateAuthorCommand.class));
          case "7" -> invoker.setCommand(context.getBean(UpdateNewsCommand.class));
          case "8" -> invoker.setCommand(context.getBean(UpdateAuthorCommand.class));
          case "9" -> invoker.setCommand(context.getBean(DeleteNewsByIdCommand.class));
          case "10" -> invoker.setCommand(context.getBean(DeleteAuthorByIdCommand.class));
          case "0" -> System.exit(0);
          default -> System.out.println(COMMAND_NOT_FOUND);
        }
      } catch (RuntimeException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}
