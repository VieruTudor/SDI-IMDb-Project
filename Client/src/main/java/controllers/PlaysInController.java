package controllers;

import domain.PlaysIn;
import interfaces.IPlaysInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class PlaysInController implements FuturePlaysInController {

    @Autowired
    private IPlaysInService playsInController;


    @Override
    public void addPlaysIn(int movieId, int actorId, String role) {
        playsInController.addPlaysIn(movieId, actorId, role);
    }

    @Override
    public void deletePlaysIn(int movieId, int actorId) {
        playsInController.deletePlaysIn(movieId, actorId);
    }

    @Override
    public void updatePlaysIn(int movieId, int actorId, String role) {
        playsInController.updatePlaysIn(movieId, actorId, role);
    }

    @Override
    public Iterable<PlaysIn> getAllPlaysIn() {
        return playsInController.getAllPlaysIn();
    }

    @Override
    public Iterable<PlaysIn> getPlayInRelationAfterRole(String role) {
        return playsInController.getPlaysInRelationAfterRole(role);
    }

    @Override
    public Double getPercentageOfRolesOfActors(String role) {
        return playsInController.getPercentageOfRolesOfActors(role);
    }
}
