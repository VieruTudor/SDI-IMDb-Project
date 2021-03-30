package controllers;

import domain.Director;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;
import java.util.stream.Collectors;

public class DirectorController implements FutureDirectorController {

    @Autowired
    private ExecutorService executorService;

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
    public CompletableFuture<Iterable<Director>> getAllDirectors() {
        Callable<Iterable<Director>> callable = () ->
        {
            Message message = new Message("DirectorController:getAllDirectors");
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> {
                            try {
                                return NetworkUtils.deserializeObject(
                                        string,
                                        Director.class
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return CompletableFuture.supplyAsync(() -> {
            try {
                return callable.call();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        });
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
                        .map(string -> {
                            try {
                                return NetworkUtils.deserializeObject(string, Director.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
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
