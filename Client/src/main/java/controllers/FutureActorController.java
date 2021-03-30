package controllers;

import domain.Actor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface FutureActorController {
    void addActor(int id, String name, int age, int fame);

    void deleteActor(int id);

    void updateActor(int id, String name, int age, int fame);

    Iterable<Actor> getAllActors();

    Iterable<Actor> getActorsWithFameBetween(int lower, int upper);

    Double getPercentageOfFamousActors(int fame);
}
