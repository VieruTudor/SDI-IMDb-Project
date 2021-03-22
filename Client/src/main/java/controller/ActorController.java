package controller;

import domain.Actor;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ActorController implements IActorController{

    private final ExecutorService executorService;

    public ActorController(ExecutorService executorService){
        this.executorService = executorService;
    }

    @Override
    public Future<Void> addActor(int id, String name, int age, int fame) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("ActorController:addActor");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(age));
            message.addRow(NetworkUtils.serialiseObject(fame));


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
    public Future<Void> deleteActor(int id) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("ActorController:deleteActor");
            message.addRow(NetworkUtils.serialiseObject(id));
            Message response = TCPClient.sendAndReceive(message);
            if(NetworkUtils.isSuccess(response)){
                return null;
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Void> updateActor(int id, String name, int age, int fame) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("ActorController:updateActor");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(age));
            message.addRow(NetworkUtils.serialiseObject(fame));
            Message response = TCPClient.sendAndReceive(message);

            if(NetworkUtils.isSuccess(response)){
                return null;
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<Actor>> getAllActors() {
        Callable<Iterable<Actor>> callable = () ->
        {
            Message message = new Message("ActorController:getAllActors");
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(
                                string,
                                Actor.class
                        ))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<Actor>> getActorsWithFameBetween(int lower, int upper) {
        Callable<Iterable<Actor>> callable = () ->
        {
            Message message = new Message("ActorController:getActorsWithFameBetween");
            message.addRow(NetworkUtils.serialiseObject(lower));
            message.addRow(NetworkUtils.serialiseObject(upper));
            Message response = TCPClient.sendAndReceive(message);

            if(NetworkUtils.isSuccess(response)){
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(string, Actor.class))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Double> getPercentageOfFamousActors(int fame) {
        Callable<Double> callable = () ->
        {
            Message message = new Message("ActorController:getPercentageOfFamousActors");

            message.addRow(NetworkUtils.serialiseObject(fame));

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
