package com.mjc.school.service.implementation;

import static com.mjc.school.service.exceptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mapper.NewsModelMapper;
import com.mjc.school.service.validator.aspects.ValidateNews;
import com.mjc.school.service.validator.aspects.ValidateNewsId;

@Service("newsService")
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
  private final NewsModelMapper newsModelMapper = Mappers.getMapper(NewsModelMapper.class);
  private final BaseRepository<NewsModel, Long> newsRepository;

  @Autowired
  public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
    this.newsRepository = newsRepository;
  }

  @Override
  public List<NewsDtoResponse> readAll() {
    return newsModelMapper.modelListToDtoList(newsRepository.readAll());
  }

  @Override
  @ValidateNewsId
  public NewsDtoResponse readById(Long newsId) {
    Optional<NewsModel> newsOptional = newsRepository.readById(newsId);
    if (newsOptional.isPresent()) {
      return newsModelMapper.modelToDto(newsOptional.get());
    }
    throw new NotFoundException(
        String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId));
    // if (!newsRepository.existById(newsId)) {
    // throw new NotFoundException(
    // String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId));
    // }
    // NewsModel newsModel = newsRepository.readById(newsId).get();
    // return newsModelMapper.modelToDto(newsModel);
  }

  @Override
  @ValidateNews
  public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
    NewsModel model = newsModelMapper.dtoToModel(dtoRequest);
    LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    model.setCreateDate(date);
    model.setLastUpdatedDate(date);
    NewsModel newsModel = newsRepository.create(model);
    return newsModelMapper.modelToDto(newsModel);
  }

  @Override
  @ValidateNewsId
  @ValidateNews
  public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
    if (newsRepository.existById(dtoRequest.id())) {
      NewsModel model = newsModelMapper.dtoToModel(dtoRequest);
      LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      model.setLastUpdatedDate(date);
      NewsModel newsModel = newsRepository.update(model);
      return newsModelMapper.modelToDto(newsModel);
    } else {
      throw new NotFoundException(
          String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
    }
  }

  @Override
  @ValidateNewsId
  public boolean deleteById(Long newsId) {
    if (newsRepository.existById(newsId)) {
      return newsRepository.deleteById(newsId);
    }
    throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId));
  }
}
