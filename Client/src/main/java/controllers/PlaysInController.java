package controllers;

import domain.PlaysIn;
import interfaces.IPlaysInController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PlaysInController implements FuturePlaysInController {
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private IPlaysInController playsInController;

    @Override
    public Future<Void> addPlaysIn(int movieId, int actorId, String role) {
        return null;
    }

    @Override
    public Future<Void> deletePlaysIn(int movieId, int actorId) {
        return null;
    }

    @Override
    public Future<Void> updatePlaysIn(int movieId, int actorId, String role) {
        return null;
    }

    @Override
    public Future<Iterable<PlaysIn>> getAllPlaysIn() {
        Callable<Iterable<PlaysIn>> callable = () ->
        {
            return playsInController.getAllPlaysIn();
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<PlaysIn>> getPlayInRelationAfterRole(String role) {
        return null;
    }

    @Override
    public Future<Double> getPercentageOfRolesOfActors(String role) {
        return null;
    }

}
