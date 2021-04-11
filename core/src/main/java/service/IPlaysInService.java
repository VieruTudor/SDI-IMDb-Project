package service;

import model.Pair;
import model.PlaysIn;

public interface IPlaysInService {
    PlaysIn addPlaysIn(PlaysIn playsIn);

    void deletePlaysIn(Pair<Integer, Integer> id);

    PlaysIn updatePlaysIn(PlaysIn playsIn);

    PlaysIn getById(Pair<Integer, Integer> id);

    Iterable<PlaysIn> getAllPlaysIns();

    Iterable<PlaysIn> getAllPlaysInWithRole(String role);

    Double getPercentageOfRolesOfActors(String role);
}
