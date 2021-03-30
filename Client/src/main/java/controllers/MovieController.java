package controllers;

import domain.Movie;
import networking.Message;
import networking.TCPClient;
import networking.Utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MovieController implements FutureMovieController {

    @Autowired
    private ExecutorService executorService;


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
                        .map(string -> {
                            try {
                                return NetworkUtils.deserializeObject(
                                        string,
                                        Movie.class
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
                        .map(string -> {
                            try {
                                return NetworkUtils.deserializeObject(string, Movie.class);
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
    public Future<Double> getPercentageOfMoviesThisDecade(int decade) {
        Callable<Double> callable = () ->
        {
            Message message = new Message("DirectorController:getPercentageOfMoviesThisDecade");

            message.addRow(NetworkUtils.serialiseObject(decade));

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
