package controller;

import domain.Actor;

import java.util.concurrent.Future;

public interface IActorController {
    Future<Void> addActor(int id, String name, int age, int fame);

    Future<Void> deleteActor(int id);

    Future<Void> updateActor(int id, String name, int age, int fame);

    Future<Iterable<Actor>> getAllActors();

    Future<Iterable<Actor>> getActorsWithFameBetween(int lower, int upper);

    Future<Double> getPercentageOfFamousActors(int fame);
}
