package controller;

import domain.Director;

import java.util.concurrent.Future;

public interface IDirectorController {
    Future<Void> addDirector(int id, String name, int age);

    Future<Void> deleteDirector(int id);

    Future<Void> updateDirector(int id, String name, int age);

    Future<Iterable<Director>> getAllDirectors();

    Future<Iterable<Director>> getDirectorsWithAgeSmallerThen(int margin);

    Future<Double> getPercentageOfYoungDirectors(int age);
}
