package controller;

import domain.Director;

import java.util.Set;

public interface IDirectorController {
    void addDirector(int id, String name, int age);

    void deleteDirector(int id);

    void updateDirector(int id, String name, int age);

    Iterable<Director> getAllDirectors();

    Set<Director> getDirectorsWithAgeSmallerThen(int margin);
}
