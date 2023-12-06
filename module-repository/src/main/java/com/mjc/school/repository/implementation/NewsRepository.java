package com.mjc.school.repository.implementation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.data.NewsDataSource;
import com.mjc.school.repository.model.NewsModel;

@Repository("newsRepository")
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final NewsDataSource newsDataSource;

    @Autowired
    public NewsRepository(NewsDataSource dataSource) {
        this.newsDataSource = dataSource;
    }

    @Override
    public List<NewsModel> readAll() {
        return newsDataSource.getNewsList();
    }

    @Override
    public Optional<NewsModel> readById(Long newsId) {
        return newsDataSource.getNewsList().stream()
                .filter(news -> news.getId().equals(newsId))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel model) {
        List<NewsModel> newsModels = newsDataSource.getNewsList();
        newsModels.sort(Comparator.comparing(NewsModel::getId));
        if (!newsModels.isEmpty()) {
            model.setId(newsModels.get(newsModels.size() - 1).getId() + 1);
        } else {
            model.setId(1L);
        }
        newsModels.add(model);
        return model;
    }

    @Override
    public NewsModel update(NewsModel model) {
        Optional<NewsModel> newsOptional = readById(model.getId());
        if (newsOptional.isEmpty()) {
            return null;
        }
        NewsModel newsModel = newsOptional.get();
        newsModel.setTitle(model.getTitle());
        newsModel.setContent(model.getContent());
        newsModel.setLastUpdatedDate(model.getLastUpdatedDate());
        newsModel.setAuthorId(model.getAuthorId());
        return newsModel;
    }

    @Override
    public boolean deleteById(Long newsId) {
        return newsDataSource.getNewsList().removeIf(x -> x.getId().equals(newsId));
    }

    @Override
    public boolean existById(Long newsId) {
        return newsDataSource.getNewsList().stream().anyMatch(news -> news.getId().equals(newsId));
    }
}
