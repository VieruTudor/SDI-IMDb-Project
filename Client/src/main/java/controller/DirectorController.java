package controller;

import domain.Director;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class DirectorController implements IDirectorController{
    private final ExecutorService executorService;

    public DirectorController(ExecutorService executorService){
        this.executorService = executorService;
    }
    @Override
    public Future<Void> addDirector(int id, String name, int age) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("DirectorController:addDirector");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(age));

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
    public Future<Void> deleteDirector(int id) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("DirectorController:deleteDirector");

            message.addRow(NetworkUtils.serialiseObject(id));

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
    public Future<Void> updateDirector(int id, String name, int age) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("DirectorController:updateDirector");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(age));

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
    public Future<Iterable<Director>> getAllDirectors() {
        Callable<Iterable<Director>> callable = () ->
        {
            Message message = new Message("DirectorController:getAllDirectors");
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(
                                string,
                                Director.class
                        ))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<Director>> getDirectorsWithAgeSmallerThen(int margin) {
        Callable<Iterable<Director>> callable = () ->
        {
            Message message = new Message("DirectorController:getDirectorsWithAgeSmallerThen");
            message.addRow(NetworkUtils.serialiseObject(margin));
            Message response = TCPClient.sendAndReceive(message);

            if(NetworkUtils.isSuccess(response)){
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(string, Director.class))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Double> getPercentageOfYoungDirectors(int age) {
        Callable<Double> callable = () ->
        {
            Message message = new Message("DirectorController:getPercentageOfYoungDirectors");

            message.addRow(NetworkUtils.serialiseObject(age));

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
