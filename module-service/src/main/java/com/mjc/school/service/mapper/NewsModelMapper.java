package com.mjc.school.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Mapper
public interface NewsModelMapper {
    List<NewsDtoResponse> modelListToDtoList(List<NewsModel> newsModelList);

    NewsDtoResponse modelToDto(NewsModel newsModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    NewsModel dtoToModel(NewsDtoRequest newsModelRequest);
}
