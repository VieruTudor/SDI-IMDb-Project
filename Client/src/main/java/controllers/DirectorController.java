package controllers;

import domain.Director;

import interfaces.IDirectorController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

public class DirectorController implements FutureDirectorController {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private IDirectorController directorController;

    @Override
    public void addDirector(int id, String name, int age) {
        directorController.addDirector(id, name, age);
    }

    @Override
    public void deleteDirector(int id) {
        directorController.deleteDirector(id);
    }

    @Override
    public void updateDirector(int id, String name, int age) {
        directorController.updateDirector(id, name, age);
    }

    @Override
    public Iterable<Director> getAllDirectors() {
        return directorController.getAllDirectors();
    }

    @Override
    public Iterable<Director> getDirectorsWithAgeSmallerThen(int margin) {
        return directorController.getDirectorsWithAgeSmallerThen(margin);
    }

    @Override
    public Double getPercentageOfYoungDirectors(int age) {
        return directorController.getPercentageOfYoungDirectors(age);
    }
}
