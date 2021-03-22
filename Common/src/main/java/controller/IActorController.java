package controller;

import domain.Actor;

import java.util.Set;

public interface IActorController {
    void addActor(int id, String name, int age, int fame);

    void deleteActor(int id);

    void updateActor(int id, String name, int age, int fame);

    Iterable<Actor> getAllActors();

    Set<Actor> getActorsWithFameBetween(int lower, int upper);
}
