package com.mjc.school.service.implementation;

import static com.mjc.school.service.exceptions.ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mapper.AuthorModelMapper;
import com.mjc.school.service.validator.aspects.ValidateAuthor;
import com.mjc.school.service.validator.aspects.ValidateAuthorId;

@Service("authorService")
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final AuthorModelMapper authorModelMapper = Mappers.getMapper(AuthorModelMapper.class);
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorModelMapper.modelListToDtoList(authorRepository.readAll());
    }

    @Override
    @ValidateAuthorId
    public AuthorDtoResponse readById(Long authorId) {
        if (authorRepository.existById(authorId)) {
            AuthorModel authorModel = authorRepository.readById(authorId).get();
            return authorModelMapper.modelToDto(authorModel);
        }
        throw new NotFoundException(String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), authorId));
    }

    @Override
    @ValidateAuthor
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel model = authorModelMapper.dtoToModel(createRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdatedDate(date);
        AuthorModel authorModel = authorRepository.create(model);
        return authorModelMapper.modelToDto(authorModel);
    }

    @Override
    @ValidateAuthorId
    @ValidateAuthor
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
        if (authorRepository.existById(dtoRequest.id())) {
            AuthorModel model = authorModelMapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setLastUpdatedDate(date);
            AuthorModel authorModel = authorRepository.update(model);
            return authorModelMapper.modelToDto(authorModel);
        } else {
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    @ValidateAuthorId
    public boolean deleteById(Long authorId) {
        if (authorRepository.existById(authorId)) {
            return authorRepository.deleteById(authorId);
        }
        throw new NotFoundException(
                String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
    }

}
