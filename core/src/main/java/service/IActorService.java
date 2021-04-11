package service;

import model.Actor;

public interface IActorService {
    Actor addActor(Actor actor);

    void deleteActor(int id);

    Actor updateActor(Actor actor);

    Actor getById(int id);

    Iterable<Actor> getAllActors();

    Iterable<Actor> getActorsWithFameBetween(int lower, int upper);

    Double getPercentageOfFamousActors(int fame);
}

