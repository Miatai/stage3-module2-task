package com.mjc.school.repository.implementation;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aspects.OnDelete;
import com.mjc.school.repository.data.AuthorDataSource;
import com.mjc.school.repository.model.AuthorModel;

@Component("authorRepository")
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final AuthorDataSource authorDataSource;

    @Autowired
    public AuthorRepository(AuthorDataSource dataSource) {
        this.authorDataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return authorDataSource.getAuthorList();
    }

    @Override
    public Optional<AuthorModel> readById(Long authorId) {
        return authorDataSource.getAuthorList().stream()
                .filter(author -> authorId.equals(author.getId()))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel model) {
        List<AuthorModel> authorModels = authorDataSource.getAuthorList();
        authorModels.sort(Comparator.comparing(AuthorModel::getId));
        if (!authorModels.isEmpty()) {
            model.setId(authorModels.get(authorModels.size() - 1).getId() + 1);
        } else {
            model.setId(1L);
        }
        authorModels.add(model);
        return model;
    }

    @Override
    public AuthorModel update(AuthorModel model) {
        Optional<AuthorModel> authorOptional = readById(model.getId());
        if (authorOptional.isEmpty()) {
            return null;
        }
        AuthorModel authorModel = authorOptional.get();
        authorModel.setName(model.getName());
        authorModel.setLastUpdatedDate(model.getLastUpdatedDate());
        return authorModel;
    }

    @Override
    public boolean deleteById(Long authorId) {
        return authorDataSource.getAuthorList().removeIf(x -> x.getId().equals(authorId));
    }

    @Override
    @OnDelete
    public boolean existById(Long authorId) {
        return authorDataSource.getAuthorList().stream().anyMatch(author -> authorId.equals(author.getId()));
    }

}
