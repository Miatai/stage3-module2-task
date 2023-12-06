package com.mjc.school.repository.data;

import static com.mjc.school.repository.utils.Utils.getRandomContentByFilePath;
import static com.mjc.school.repository.utils.Utils.getRandomDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mjc.school.repository.model.AuthorModel;

@Component
@Scope("singleton")
public class AuthorDataSource {
    private static final String AUTHORS_FILE_NAME = "authors";
    private List<AuthorModel> authorList;

    public AuthorDataSource() {
        init();
    }

    private void init() {
        authorList = new ArrayList<>();
        for (long i = 1; i <= 20; i++) {
            LocalDateTime date = getRandomDate();
            authorList.add(new AuthorModel(i, getRandomContentByFilePath(AUTHORS_FILE_NAME), date, date));
        }
    }

    public List<AuthorModel> getAuthorList() {
        return authorList;
    }
}
