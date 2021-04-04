package controllers;

import domain.Director;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface FutureDirectorController {
    void addDirector(int id, String name, int age);

    void deleteDirector(int id);

    void updateDirector(int id, String name, int age);

    Iterable<Director> getAllDirectors();

    Iterable<Director> getDirectorsWithAgeSmallerThen(int margin);

    Double getPercentageOfYoungDirectors(int age);
}
