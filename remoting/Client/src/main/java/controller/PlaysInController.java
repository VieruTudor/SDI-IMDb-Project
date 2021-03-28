package controller;

import domain.PlaysIn;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class PlaysInController implements IPlaysInController{
    private final ExecutorService executorService;

    public PlaysInController(ExecutorService executorService){
        this.executorService = executorService;
    }
    @Override
    public Future<Void> addPlaysIn(int movieId, int actorId, String role) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("PlaysInController:addPlaysIn");

            message.addRow(NetworkUtils.serialiseObject(movieId));
            message.addRow(NetworkUtils.serialiseObject(actorId));
            message.addRow(NetworkUtils.serialiseObject(role));

            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return null;
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Void> deletePlaysIn(int movieId, int actorId) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("PlaysInController:deletePlaysIn");

            message.addRow(NetworkUtils.serialiseObject(movieId));
            message.addRow(NetworkUtils.serialiseObject(actorId));

            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return null;
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Void> updatePlaysIn(int movieId, int actorId, String role) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("PlaysInController:updatePlaysIn");

            message.addRow(NetworkUtils.serialiseObject(movieId));
            message.addRow(NetworkUtils.serialiseObject(actorId));
            message.addRow(NetworkUtils.serialiseObject(role));

            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return null;
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<PlaysIn>> getAllPlaysIn() {
        Callable<Iterable<PlaysIn>> callable = () ->
        {
            Message message = new Message("PlaysInController:getAllPlaysIn");
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(
                                string,
                                PlaysIn.class
                        ))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<PlaysIn>> getPlayInRelationAfterRole(String role) {
        Callable<Iterable<PlaysIn>> callable = () ->
        {
            Message message = new Message("PlaysInController:getPlayInRelationAfterRole");
            message.addRow(NetworkUtils.serialiseObject(role));
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(
                                string,
                                PlaysIn.class
                        ))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Double> getPercentageOfRolesOfActors(String role) {
        Callable<Double> callable = () ->
        {
            Message message = new Message("PlaysInController:getPercentageOfRolesOfActors");

            message.addRow(NetworkUtils.serialiseObject(role));

            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return NetworkUtils.deserializeObject(response.getBody().get(0), Double.class);
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

}
