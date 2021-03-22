package controller;

import domain.PlaysIn;

import java.util.Set;

public interface IPlaysInController {
    void addPlaysIn(int movieId, int actorId, String role);

    void deletePlaysIn(int movieId, int actorId);

    void updatePlaysIn(int movieId, int actorId, String role);

    Iterable<PlaysIn> getAllPlaysIn();

    Iterable<PlaysIn> getPlaysInRelationAfterRole(String role);

    Double getPercentageOfRolesOfActors(String role);
}
