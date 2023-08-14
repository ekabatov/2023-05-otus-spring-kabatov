package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    @Override
    public Optional<Genre> findById(Long id) {
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
