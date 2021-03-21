package controller;

import domain.PlaysIn;

import java.util.Set;

public interface PlaysInController {
    void addPlaysIn(int movieId, int actorId, String role);

    void deletePlaysIn(int movieId, int actorId);

    void updatePlaysIn(int movieId, int actorId, String role);

    Iterable<PlaysIn> getAllPlaysIn();

    Set<PlaysIn> getPlaysInRelationAfterRole(String role);
}
