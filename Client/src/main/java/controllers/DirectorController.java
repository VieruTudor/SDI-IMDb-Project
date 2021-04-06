package controllers;

import domain.Director;

import interfaces.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class DirectorController implements FutureDirectorController {


    @Autowired
    private IDirectorService directorController;

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
