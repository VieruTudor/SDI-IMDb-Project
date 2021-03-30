package controllers;

import domain.PlaysIn;
import interfaces.IPlaysInController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;


public class PlaysInController implements FuturePlaysInController {
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private IPlaysInController playsInController;


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
