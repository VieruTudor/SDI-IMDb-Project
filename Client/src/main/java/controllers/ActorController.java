package controllers;

import domain.Actor;
import interfaces.IActorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class ActorController implements FutureActorController {
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private IActorController actorController;

    @Override
    public void addActor(int id, String name, int age, int fame) {
        actorController.addActor(id, name, age, fame);

    }

    @Override
    public void deleteActor(int id) {
        actorController.deleteActor(id);
    }

    @Override
    public void updateActor(int id, String name, int age, int fame) {
        actorController.updateActor(id, name, age, fame);
    }

    @Override
    public Iterable<Actor> getAllActors() {
        return actorController.getAllActors();

    }

    @Override
    public Iterable<Actor> getActorsWithFameBetween(int lower, int upper) {
        return actorController.getActorsWithFameBetween(lower, upper);
    }

    @Override
    public Double getPercentageOfFamousActors(int fame) {
        return actorController.getPercentageOfFamousActors(fame);
    }
}