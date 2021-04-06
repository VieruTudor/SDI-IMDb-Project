package interfaces;

import domain.PlaysIn;

public interface IPlaysInService {
    void addPlaysIn(int movieId, int actorId, String role);

    void deletePlaysIn(int movieId, int actorId);

    void updatePlaysIn(int movieId, int actorId, String role);

    Iterable<PlaysIn> getAllPlaysIn();

    Iterable<PlaysIn> getPlaysInRelationAfterRole(String role);

    Double getPercentageOfRolesOfActors(String role);
}
