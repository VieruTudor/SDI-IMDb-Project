package service;

import model.Director;
import org.springframework.stereotype.Service;

@Service
public class DirectorService implements IDirectorService{
    @Override
    public Director addDirector(Director director) {
        return null;
    }

    @Override
    public void deleteDirector(int id) {

    }

    @Override
    public Director updateDirector(Director director) {
        return null;
    }

    @Override
    public Director getById(int id) {
        return null;
    }

    @Override
    public Iterable<Director> getAllDirectors() {
        return null;
    }

    @Override
    public Iterable<Director> getActorsWithAgeGreaterThen(int lower) {
        return null;
    }
}
