package com.mjc.school.repository.data;

import static com.mjc.school.repository.utils.Utils.getRandomContentByFilePath;
import static com.mjc.school.repository.utils.Utils.getRandomDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mjc.school.repository.model.NewsModel;

@Component
@Scope("singleton")
public class NewsDataSource {
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private List<NewsModel> newsList;
    private final AuthorDataSource authorData;

    @Autowired
    private NewsDataSource(AuthorDataSource authorData) {
        this.authorData = authorData;
        init();
    }

    private void init() {

        newsList = new ArrayList<>();
        Random random = new Random();
        for (long i = 1; i <= 20; i++) {
            LocalDateTime date = getRandomDate();
            newsList.add(
                    new NewsModel(
                            i,
                            getRandomContentByFilePath(NEWS_FILE_NAME),
                            getRandomContentByFilePath(CONTENT_FILE_NAME),
                            date,
                            date,
                            authorData.getAuthorList().get(random.nextInt(authorData.getAuthorList().size())).getId()));
        }
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }
}
