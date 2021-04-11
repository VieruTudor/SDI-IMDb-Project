package service;

import model.Director;

public interface IDirectorService {
    Director addDirector(Director director);

    void deleteDirector(int id);

    Director updateDirector(Director director);

    Director getById(int id);

    Iterable<Director> getAllDirectors();

    Iterable<Director> getActorsWithAgeGreaterThen(int lower);
    Double getPercentageOfYoungDirectors(int age);

}
