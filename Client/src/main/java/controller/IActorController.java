package controller;

import domain.Actor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface IActorController {
    CompletableFuture<Void> addActor(int id, String name, int age, int fame);

    CompletableFuture<Void> deleteActor(int id);

    CompletableFuture<Void> updateActor(int id, String name, int age, int fame);

    CompletableFuture<Iterable<Actor>> getAllActors();

    CompletableFuture<Iterable<Actor>> getActorsWithFameBetween(int lower, int upper);

    CompletableFuture<Double> getPercentageOfFamousActors(int fame);
}
