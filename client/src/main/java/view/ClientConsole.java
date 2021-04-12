package view;

import dto.*;
import exceptions.DuplicateException;
import exceptions.InexistentEntity;
import exceptions.ProgramException;
import exceptions.ValidException;
import model.Pair;
import model.PlaysIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class ClientConsole {

    @Autowired
    private RestTemplate restTemplate;
    String actorUrl = "http://localhost:8080/api/actors";
    String directorUrl = "http://localhost:8080/api/directors";
    String moviesUrl = "http://localhost:8080/api/movies";
    String playsInUrl = "http://localhost:8080/api/playsin";

    @PostConstruct
    private void initialize() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(System.in));
            readMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.commands.put("help", () -> System.out.println(this.commandsMenu.toString()));
        //add
        this.commands.put("add movie", this::addMovie);
        this.commands.put("add actor", this::addActor);
        this.commands.put("add plays in", this::addPlaysIn);
        this.commands.put("add director", this::addDirector);
        //list
        this.commands.put("list movies", this::showMovies);
        this.commands.put("list actors", this::showActors);
        this.commands.put("list roles", this::showPlaysIn);
        this.commands.put("list directors", this::showDirectors);
        //delete
        this.commands.put("delete actor", this::deleteActor);
        this.commands.put("delete movie", this::deleteMovie);
        this.commands.put("delete plays in", this::deletePlaysIn);
        this.commands.put("delete director", this::deleteDirector);
        //update
        this.commands.put("update actor", this::updateActor);
        this.commands.put("update movie", this::updateMovie);
        this.commands.put("update plays in", this::updatePlaysIn);
        this.commands.put("update director", this::updateDirector);
        //filter
        this.commands.put("filter actors", this::filterActor);
        this.commands.put("filter movies", this::filterMovie);
        this.commands.put("filter plays in", this::filterPlaysIn);
        this.commands.put("filter directors", this::filterDirector);
        //report
        this.commands.put("report plays in", this::reportPlaysIn);
        this.commands.put("report actors", this::reportActors);
        this.commands.put("report movies", this::reportMovies);
        this.commands.put("report directors", this::reportDirectors);
    }

    HashMap<String, Runnable> commands = new HashMap<>();
    BufferedReader reader;
    StringBuilder commandsMenu = new StringBuilder();

    private void readMenu() throws IOException {
        Path path = Paths.get("client/src/main/java/menu.txt");
        Files.readAllLines(path).stream().forEach(t -> commandsMenu.append(t).append("\n"));
    }

    private String getField(String field) throws IOException {
        System.out.println(field);
        return reader.readLine();
    }


    public void run() throws IOException {

        while (true) {
            System.out.println("help - for commands menu");
            var command = reader.readLine();

            if (command.equals("exit") || command.equals("quit"))
                return;

            else if (commands.containsKey(command))
                commands.get(command).run();
            else
                System.out.println("No such command !");
        }
    }

    // Actor methods
    private void addActor() {
        try {
            System.out.println("ID:");
            var id = Integer.parseInt(reader.readLine());
            System.out.println("Name:");
            var name = reader.readLine();
            System.out.println("Age:");
            var age = Integer.parseInt(reader.readLine());
            System.out.println("Fame:");
            var fame = Integer.parseInt(reader.readLine());

            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.postForObject(actorUrl, new ActorDto(id, name, age, fame), ActorDto.class);
                            return "Actor added";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addActor();
        }
    }

    private void updateActor() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var age = Integer.parseInt(this.getField("Age:"));
            var fame = Integer.parseInt(this.getField("Fame:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("id", id);
            var newActor = new ActorDto(id, name, age, fame);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.put(actorUrl + "/{id}", newActor, params);
                            return "Actor updated";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateActor();
        }
    }

    private void deleteActor() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            Map<String, Integer> params = new HashMap<>();
            params.put("id", id);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.delete(actorUrl + "/{id}", params);
                            return "Actor deleted";
                        } catch (RestClientException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteActor();
        }
    }

    private void filterActor() {
        System.out.println("Here you can see the actors that have a fame in a specific interval");
        try {
            var low = Integer.parseInt(this.getField("Low value:"));
            var high = Integer.parseInt(this.getField("High value:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("low", low);
            params.put("high", high);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(actorUrl + "/filterByFame/{low}&{high}", ActorsDto.class, params)
            )
                    .thenAcceptAsync(actors -> actors.getActors().forEach(System.out::println));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterActor();
        }

    }

    private void reportActors() {
        try {
            var fame = Integer.parseInt(this.getField("fame"));
            Map<String, Integer> params = new HashMap<>();
            params.put("fame", fame);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(actorUrl + "/reportActors/{fame}", Double.class, params)
            )
                    .thenAcceptAsync(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showActors() {
        CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject(actorUrl, ActorsDto.class)
        )
                .thenAcceptAsync(actors -> actors.getActors().forEach(System.out::println));
    }

    // Movie methods
    private void addMovie() {
        try {
            System.out.println("ID:");
            var id = Integer.parseInt(reader.readLine());
            System.out.println("Name:");
            var name = reader.readLine();
            System.out.println("Rating:");
            var rating = Integer.parseInt(reader.readLine());
            System.out.println("Year:");
            var year = Integer.parseInt(reader.readLine());
            System.out.println("Director Id:");
            var directorId = Integer.parseInt(reader.readLine());

            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.postForObject(moviesUrl, new MovieDto(id, name, rating, year, directorId), MovieDto.class);
                            return "Movie added";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addMovie();
        }
    }

    private void updateMovie() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var rating = Integer.parseInt(this.getField("Rating:"));
            var year = Integer.parseInt(this.getField("Year:"));
            var directorID = Integer.parseInt(this.getField("Director ID:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("id", id);
            var newMovie = new MovieDto(id, name, rating, year, directorID);

            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.put(moviesUrl + "/{id}", newMovie, params);
                            return "Movie updated";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateMovie();
        }
    }

    private void deleteMovie() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            Map<String, Integer> params = new HashMap<>();
            params.put("id", id);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.delete(moviesUrl + "/{id}", params);
                            return "Movie deleted";
                        } catch (RestClientException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            )
                    .thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteMovie();
        }
    }

    private void filterMovie() {
        System.out.println("Here you can see the movies that have a rating greater then the one you insert");
        try {
            var rating = Integer.parseInt(this.getField("Rating:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("rating", rating);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(moviesUrl + "/filterByRating/{rating}", MoviesDto.class, params)
            )
                    .thenAcceptAsync(movies -> movies.getMovies().forEach(System.out::println));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterMovie();
        }
    }

    private void reportMovies() {
        try {
            var decade = Integer.parseInt(this.getField("decade"));
            Map<String, Integer> params = new HashMap<>();
            params.put("decade", decade);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(moviesUrl + "/reportMovies/{decade}", Double.class, params)
            )
                    .thenAcceptAsync(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showMovies() {
        CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject(moviesUrl, MoviesDto.class)
        )
                .thenAcceptAsync(movies -> movies.getMovies().forEach(System.out::println));
    }

    // Director methods
    private void addDirector() {
        try {
            System.out.println("ID:");
            var id = Integer.parseInt(reader.readLine());
            System.out.println("Name");
            var name = reader.readLine();
            System.out.println("Age");
            var age = Integer.parseInt(reader.readLine());
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.postForObject(directorUrl, new DirectorDto(id, name, age), DirectorDto.class);
                            return "Director added";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addDirector();
        }
    }

    private void updateDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var age = Integer.parseInt(this.getField("Age:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("id", id);
            var newDirector = new DirectorDto(id, name, age);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.put(directorUrl + "/{id}", newDirector, params);
                            return "Director updated";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateDirector();
        }
    }

    private void deleteDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            Map<String, Integer> params = new HashMap<>();
            params.put("id", id);
            CompletableFuture.supplyAsync(
                    () -> {
                        try{
                            restTemplate.delete(directorUrl + "/{id}", params);
                            return "Director deleted";
                        } catch (RestClientException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteDirector();
        }
    }

    private void filterDirector() {
        System.out.println("Here you can see the directors younger then an inputted age");
        try {
            var age = Integer.parseInt(this.getField("Age:"));
            HashMap<String, Integer> params = new HashMap<>();
            params.put("age", age);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(directorUrl + "/filterByAge/{age}", DirectorsDto.class, params)
            )
                    .thenAcceptAsync(directors -> directors.getDirectors().forEach(System.out::println));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterDirector();
        }
    }

    private void reportDirectors() {
        try {
            var age = Integer.parseInt(this.getField("age"));
            Map<String, Integer> params = new HashMap<>();
            params.put("age", age);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(directorUrl + "/reportDirectors/{age}", Double.class, params)
            )
                    .thenAcceptAsync(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showDirectors() {
        CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject(directorUrl, DirectorsDto.class)
        )
                .thenAcceptAsync(directors -> directors.getDirectors().forEach(System.out::println));

    }

    // Plays in methods
    private void addPlaysIn() {
        try {
            System.out.println("Movie ID:");
            var movieId = Integer.parseInt(reader.readLine());
            System.out.println("Actor ID:");
            var actorId = Integer.parseInt(reader.readLine());
            System.out.println("Role: ");
            var role = reader.readLine();

            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.postForObject(playsInUrl, new PlaysInDto(movieId, actorId, role), PlaysInDto.class);
                            return "Role added";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addPlaysIn();
        }
    }

    private void updatePlaysIn() {
        try {
            var movieID = Integer.parseInt(this.getField("Movie ID:"));
            var actorID = Integer.parseInt(this.getField("Actor ID:"));
            var role = this.getField("Role:");
            Pair<Integer, Integer> id = new Pair<>();
            id.setFirst(movieID);
            id.setSecond(actorID);
            HashMap<String, Integer> params = new HashMap<>();
            params.put("id1", movieID);
            params.put("id2", actorID);
            var newPlaysIn = new PlaysInDto(movieID, actorID, role);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.put(playsInUrl + "/{id1}&{id2}", newPlaysIn, params);
                            return "Plays in updated";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updatePlaysIn();
        }
    }

    private void deletePlaysIn() {
        try {
            var movieID = Integer.parseInt(this.getField("Movie ID:"));
            var actorID = Integer.parseInt(this.getField("Actor ID:"));
            Pair<Integer, Integer> id = new Pair<>();
            id.setFirst(movieID);
            id.setSecond(actorID);
            Map<String, Pair<Integer, Integer>> params = new HashMap<>();
            params.put("id", id);
            CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            restTemplate.delete(playsInUrl + "/{id}", params);
                            return "Role deleted";
                        } catch (RestClientException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deletePlaysIn();
        }
    }

    private void filterPlaysIn() {
        System.out.println("Here you can see the relations of movies and actors based on the roles");
        try {
            var role = this.getField("Role:");
            Map<String, String> params = new HashMap<>();
            params.put("role", role);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(playsInUrl + "/filterByRole/{role}", PlaysInsDto.class, params)
            )
                    .thenAcceptAsync(playsIn -> playsIn.getPlaysIns().forEach(System.out::println));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterPlaysIn();
        }
    }

    private void reportPlaysIn() {
        try {
            var role = this.getField("Role:");
            Map<String, String> params = new HashMap<>();
            params.put("role", role);
            CompletableFuture.supplyAsync(
                    () -> restTemplate.getForObject(playsInUrl + "reportPlaysIn/{role}", String.class, params)
            )
                    .thenAcceptAsync(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showPlaysIn() {
        CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject(playsInUrl, PlaysInsDto.class)
        )
                .thenAcceptAsync(playsIn -> playsIn.getPlaysIns().forEach(System.out::println));
    }

}
