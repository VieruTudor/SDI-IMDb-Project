package service;

import model.Pair;
import model.PlaysIn;
import org.springframework.stereotype.Service;

@Service
public class PlaysInService implements IPlaysInService{
    @Override
    public PlaysIn addPlaysIn(PlaysIn playsIn) {
        return null;
    }

    @Override
    public void deletePlaysIn(Pair<Integer, Integer> id) {

    }

    @Override
    public PlaysIn updatePlaysIn(PlaysIn playsIn) {
        return null;
    }

    @Override
    public PlaysIn getById(Pair<Integer, Integer> id) {
        return null;
    }

    @Override
    public Iterable<PlaysIn> getAllPlaysIns() {
        return null;
    }

    @Override
    public Iterable<PlaysIn> getAllPlaysInWithRole(String role) {
        return null;
    }
}
