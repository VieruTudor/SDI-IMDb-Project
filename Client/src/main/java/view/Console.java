package view;

import controller.ActorController;
import controller.DirectorController;
import controller.MovieController;
import controller.PlaysInController;
import domain.Actor;
import domain.Director;
import domain.Movie;
import domain.PlaysIn;
import exception.DuplicateException;
import exception.InexistentEntity;
import exception.ProgramException;
import exception.ValidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Console {
    private ActorController actorController;
    private DirectorController directorController;
    private MovieController movieController;
    private PlaysInController playsInController;
    HashMap<String, Runnable> commands = new HashMap<>();
    BufferedReader reader;
    StringBuilder commandsMenu = new StringBuilder();

    private void readMenu() throws IOException {
        Path path = Paths.get("Client/src/main/java/menu.txt");
        Files.readAllLines(path).stream().forEach(t -> commandsMenu.append(t).append("\n"));
    }

    public Console(ActorController actorController, DirectorController directorController, MovieController movieController, PlaysInController playsInController) throws IOException {
        readMenu();
        this.actorController = actorController;
        this.directorController = directorController;
        this.movieController = movieController;
        this.playsInController = playsInController;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
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

    private void reportDirectors() {
        try{
            var age = Integer.parseInt(this.getField("age"));
            Double percentage = this.directorController.getPercentageOfYoungDirectors(age).get();
            System.out.println(percentage + "% of directors are young");

        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());;
        }
    }

    private void reportMovies() {
        try{
            var decade = Integer.parseInt(this.getField("decade"));
            Double percentage = this.movieController.getPercentageOfMoviesThisDecade(decade).get();
            System.out.println(percentage + "% of movies appeared this decade");

        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reportActors() {
        try{
            var fame = Integer.parseInt(this.getField("fame"));
            Double percentage = this.actorController.getPercentageOfFamousActors(fame).get();
            System.out.println(percentage + "% of actors are famous");

        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reportPlaysIn() {
        try{
            var role = this.getField("Role:");
            Double percent = this.playsInController.getPercentageOfRolesOfActors(role).get();
            System.out.println(percent + "% of actors have " + role + " as their role");
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterDirector() {
        System.out.println("Here you can see the directors younger then an inputted age");
        try {
            var age = Integer.parseInt(this.getField("Age:"));
            var directors = this.directorController.getDirectorsWithAgeSmallerThen(age).get();
            directors.forEach(System.out::println);

        } catch (InterruptedException | ExecutionException | IOException e) {
            System.out.println(e.getMessage());
            this.filterDirector();
        }
    }

    private void filterPlaysIn() {
        System.out.println("Here you can see the relations of movies and actors based on the roles");
        try {
            var role = this.getField("Role:");
            var playsIns = this.playsInController.getPlayInRelationAfterRole(role).get();
            playsIns.forEach(System.out::println);

        } catch (InterruptedException | ExecutionException | IOException e) {
            System.out.println(e.getMessage());
            this.filterPlaysIn();
        }
    }

    private void filterMovie() {
        System.out.println("Here you can see the movies that have a rating greater then the one you insert");
        try {
            var rating = Integer.parseInt(this.getField("Rating:"));
            var movies = this.movieController.getMoviesWithRatingHigherThan(rating).get();
            movies.forEach(System.out::println);

        } catch (InterruptedException | ExecutionException | IOException e) {
            System.out.println(e.getMessage());
            this.filterMovie();
        }
    }

    private void filterActor() {
        System.out.println("Here you can see the actors that have a fame in a specific interval");
        try {
            var low = Integer.parseInt(this.getField("Low value:"));
            var high = Integer.parseInt(this.getField("High value:"));
            var actors = this.actorController.getActorsWithFameBetween(low, high).get();
            actors.forEach(System.out::println);

        } catch (InterruptedException | ExecutionException | IOException e) {
            System.out.println(e.getMessage());
            this.filterActor();
        }
    }

    private void updateDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var age = Integer.parseInt(this.getField("Age:"));
            this.directorController.updateDirector(id, name, age);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateDirector();
        }
    }

    private void updatePlaysIn() {
        try {
            var movieID = Integer.parseInt(this.getField("Movie ID:"));
            var actorID = Integer.parseInt(this.getField("Actor ID:"));
            var role = this.getField("Role:");
            this.playsInController.updatePlaysIn(movieID, actorID, role);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updatePlaysIn();
        }
    }

    private void updateMovie() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var rating = Integer.parseInt(this.getField("Rating:"));
            var year = Integer.parseInt(this.getField("Year:"));
            var directorID = Integer.parseInt(this.getField("Director ID:"));
            this.movieController.updateMovie(id, name, rating, year, directorID);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateMovie();
        }
    }

    private void updateActor() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var age = Integer.parseInt(this.getField("Age:"));
            var fame = Integer.parseInt(this.getField("Fame:"));
            this.actorController.updateActor(id, name, age, fame);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateActor();
        }
    }

    private void deleteDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.directorController.deleteDirector(id);
        } catch (IOException | InexistentEntity e ) {
            System.out.println(e.getMessage());
            this.deleteDirector();
        }
    }

    private void deletePlaysIn() {
        try {
            var movieID = Integer.parseInt(this.getField("Movie ID:"));
            var actorID = Integer.parseInt(this.getField("Actor ID:"));
            this.playsInController.deletePlaysIn(movieID, actorID);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deletePlaysIn();
        }
    }

    private void deleteMovie() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.movieController.deleteMovie(id);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteMovie();
        }
    }

    private void deleteActor() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.actorController.deleteActor(id);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteActor();
        }
    }

    private void addDirector() {
        try {
            System.out.println("ID:");
            var id = Integer.parseInt(reader.readLine());
            System.out.println("Name");
            var name = reader.readLine();
            System.out.println("Age");
            var age = Integer.parseInt(reader.readLine());
            this.directorController.addDirector(id, name, age);
        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addDirector();
        }
    }

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
            this.movieController.addMovie(id, name, rating, year, directorId);
        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addMovie();
        }
    }

    public void addActor() {
        try {
            System.out.println("ID:");
            var id = Integer.parseInt(reader.readLine());
            System.out.println("Name:");
            var name = reader.readLine();
            System.out.println("Age:");
            var age = Integer.parseInt(reader.readLine());
            System.out.println("Fame:");
            var fame = Integer.parseInt(reader.readLine());
            this.actorController.addActor(id, name, age, fame);
        } catch (IOException | ValidException | DuplicateException e) {
            System.out.println(e.getMessage());
            this.addActor();
        }
    }

    public void addPlaysIn() {
        try {
            System.out.println("Movie ID:");
            var movieId = Integer.parseInt(reader.readLine());
            System.out.println("Actor ID:");
            var actorId = Integer.parseInt(reader.readLine());
            System.out.println("Role: ");
            var role = reader.readLine();

            this.playsInController.addPlaysIn(movieId, actorId, role);
        } catch (IOException | ValidException | DuplicateException e ) {
            System.out.println(e.getMessage());
            this.addPlaysIn();
        }
    }

    public void showMovies(){
        try{
            var result = this.movieController.getAllMovies().get();
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e){
            System.out.println(e.getMessage());
        }
    }

    public void showActors(){
        try{
            var result = this.actorController.getAllActors().get();
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showPlaysIn(){
        try{
            var result = this.playsInController.getAllPlaysIn().get();
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e) {
        System.out.println(e.getMessage());
        }
    }

    private void showDirectors(){
        try {
            var result = this.directorController.getAllDirectors().get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
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
    public static String handleException(ProgramException e){
        return e.getMessage();
    }
}
