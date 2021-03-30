package controllers;

import domain.PlaysIn;

import java.util.concurrent.Future;

public interface FuturePlaysInController {
    Future<Void> addPlaysIn(int movieId, int actorId, String role);

    Future<Void> deletePlaysIn(int movieId, int actorId);

    Future<Void> updatePlaysIn(int movieId, int actorId, String role);

    Future<Iterable<PlaysIn>> getAllPlaysIn();

    Future<Iterable<PlaysIn>> getPlayInRelationAfterRole(String role);

    Future<Double> getPercentageOfRolesOfActors(String role);
}
