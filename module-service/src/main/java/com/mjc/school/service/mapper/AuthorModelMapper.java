package com.mjc.school.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;

@Mapper
public interface AuthorModelMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> authorModelList);

    AuthorDtoResponse modelToDto(AuthorModel authorModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdatedDate", ignore = true)
    AuthorModel dtoToModel(AuthorDtoRequest authorModelRequest);
}
