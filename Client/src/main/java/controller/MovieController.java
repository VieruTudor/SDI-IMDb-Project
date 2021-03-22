package controller;

import domain.Actor;
import domain.Movie;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MovieController implements IMovieController{
    private final ExecutorService executorService;

    public MovieController(ExecutorService executorService){
        this.executorService = executorService;
    }

    @Override
    public Future<Void> addMovie(int id, String name, int rating, int year, int directorId) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("MovieController:addMovie");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(year));
            message.addRow(NetworkUtils.serialiseObject(directorId));

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
    public Future<Void> deleteMovie(int id) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("MovieController:deleteMovie");

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
    public Future<Void> updateMovie(int id, String name, int rating, int year, int directorId) {
        Callable<Void> callable = () ->
        {
            Message message = new Message("MovieController:updateMovie");

            message.addRow(NetworkUtils.serialiseObject(id));
            message.addRow(NetworkUtils.serialiseObject(name));
            message.addRow(NetworkUtils.serialiseObject(year));
            message.addRow(NetworkUtils.serialiseObject(directorId));

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
    public Future<Iterable<Movie>> getAllMovies() {
        Callable<Iterable<Movie>> callable = () ->
        {
            Message message = new Message("MovieController:getAllMovies");
            Message response = TCPClient.sendAndReceive(message);
            if (NetworkUtils.isSuccess(response))
            {
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(
                                string,
                                Movie.class
                        ))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);
    }

    @Override
    public Future<Iterable<Movie>> getMoviesWithRatingHigherThan(int margin) {
        Callable<Iterable<Movie>> callable = () ->
        {
            Message message = new Message("MovieController: getMoviesWithRatingHigherThan");
            message.addRow(NetworkUtils.serialiseObject(margin));
            Message response = TCPClient.sendAndReceive(message);

            if(NetworkUtils.isSuccess(response)){
                return response.getBody().stream()
                        .map(string -> NetworkUtils.deserializeObject(string, Movie.class))
                        .collect(Collectors.toUnmodifiableList());
            }
            NetworkUtils.checkException(response);
            throw new RuntimeException("Received response was invalid");
        };
        return executorService.submit(callable);

    }
}
