package controller;

import domain.Director;

public interface IDirectorController {
    void addDirector(int id, String name, int age);

    void deleteDirector(int id);

    void updateDirector(int id, String name, int age);

    Iterable<Director> getAllDirectors();

    Iterable<Director> getDirectorsWithAgeSmallerThen(int margin);

    Double getPercentageOfYoungDirectors(int age);
}
