package com.mjc.school.helper;

public class MenuHelper {

  public void printMainMenu() {
    System.out.println("""
                Enter the number of operation:
                1 - Get all news. 
                2 - Get all authors.
                3 - Get news by id
                4 - Get author by id.
                5 - Create news.
                6 - Create author.
                7 - Update news.
                8 - Update author.
                9 - Remove news by id.
                10 - Remove author by id.
                0 - Exit.""");
  }
}
