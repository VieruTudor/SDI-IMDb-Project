package controllers;

import domain.Director;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;
import java.util.stream.Collectors;

public class DirectorController implements FutureDirectorController {

    @Autowired
    private ExecutorService executorService;

    @Override
    public Future<Void> addDirector(int id, String name, int age) {
        return null;
    }

    @Override
    public Future<Void> deleteDirector(int id) {
        return null;
    }

    @Override
    public Future<Void> updateDirector(int id, String name, int age) {
        return null;
    }

    @Override
    public CompletableFuture<Iterable<Director>> getAllDirectors() {
        return null;
    }

    @Override
    public Future<Iterable<Director>> getDirectorsWithAgeSmallerThen(int margin) {
        return null;
    }

    @Override
    public Future<Double> getPercentageOfYoungDirectors(int age) {
        return null;
    }


}
