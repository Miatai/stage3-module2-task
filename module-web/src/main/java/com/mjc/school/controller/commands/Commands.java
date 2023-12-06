package com.mjc.school.controller.commands;

import static com.mjc.school.controller.utils.Constant.COMMAND;

public enum Commands {
  GET_ALL_NEWS(1, "Get all news."),
  GET_ALL_AUTHORS(2, "Get all authors."),
  GET_NEWS_BY_ID(3, "Get news by id."),
  GET_AUTHOR_BY_ID(4, "Get author by id."),
  CREATE_NEWS(5, "Create news."),
  CREATE_AUTHOR(6, "Create author."),
  UPDATE_NEWS(7, "Update news."),
  UPDATE_AUTHOR(8, "Update author."),
  REMOVE_NEWS_BY_ID(9, "Remove news by id."),
  REMOVE_AUTHOR_BY_ID(10, "Remove author by id"),
  EXIT(0, "Exit.");

  private final Integer commandNumber;
  private final String command;

  Commands(Integer commandNumber, String command) {
    this.commandNumber = commandNumber;
    this.command = command;
  }

  public Integer getCommandNumber(){
    return commandNumber;
  }

  public String getCommand() {
    return COMMAND + command;
  }

  public String getCommandWithNumber() {
    return commandNumber + " - " + command;
  }
}
