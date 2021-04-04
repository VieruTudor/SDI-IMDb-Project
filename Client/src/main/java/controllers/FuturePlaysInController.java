package controllers;

import domain.PlaysIn;

import java.util.concurrent.Future;

public interface FuturePlaysInController {
    void addPlaysIn(int movieId, int actorId, String role);

    void deletePlaysIn(int movieId, int actorId);

    void updatePlaysIn(int movieId, int actorId, String role);

    Iterable<PlaysIn> getAllPlaysIn();

    Iterable<PlaysIn> getPlayInRelationAfterRole(String role);

    Double getPercentageOfRolesOfActors(String role);
}
