package controller;

import domain.PlaysIn;

import java.util.concurrent.Future;

public interface IPlaysInController {
    Future<Void> addPlaysIn(int movieId, int actorId, String role);

    Future<Void> deletePlaysIn(int movieId, int actorId);

    Future<Void> updatePlaysIn(int movieId, int actorId, String role);

    Future<Iterable<PlaysIn>> getAllPlaysIn();

    Future<Iterable<PlaysIn>> getPlaysInRelationAfterRole(String role);
}
