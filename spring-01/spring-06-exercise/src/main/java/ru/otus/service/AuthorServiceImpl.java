package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Override
    public Optional<Author> findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
