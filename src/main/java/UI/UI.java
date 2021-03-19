package UI;

import controller.Controller;
import domain.Actor;
import domain.Director;
import domain.Movie;
import domain.PlaysIn;
import exception.DuplicateException;
import exception.InexistentEntity;
import exception.ValidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

public class UI {
    private final Controller controller;
    HashMap<String, Runnable> commands = new HashMap<>();
    BufferedReader reader;
    String commandsMenu;

    public UI(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.commands.put("help", () -> System.out.println(this.commandsMenu));

        // add
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

        // add user commands
        this.commandsMenu = "add movie - to add a movie\n";
        this.commandsMenu += "add actor - to add an actor\n";
        this.commandsMenu += "add plays in - to add a plays in relation\n";
        this.commandsMenu += "add director - to add a director\n";
        // list user commands
        this.commandsMenu += "list movies - to list all movie\n";
        this.commandsMenu += "list actors - to list all actors\n";
        this.commandsMenu += "list roles - to list all play in relations\n";
        this.commandsMenu += "list directors - to list all directors\n";
        // delete user commands
        this.commandsMenu += "delete actor - to delete an actor\n";
        this.commandsMenu += "delete movie - to delete a movie\n";
        this.commandsMenu += "delete plays in - to delete a plays in relation\n";
        this.commandsMenu += "delete director - to delete a director\n";
        // update user commands
        this.commandsMenu += "update actor - to update an actor\n";
        this.commandsMenu += "update movie - to update a movie\n";
        this.commandsMenu += "update plays in - to update a plays in relation\n";
        this.commandsMenu += "update director - to update a director\n";
        // filter user commands
        this.commandsMenu += "filter actors - to filter the list of actors\n";
        this.commandsMenu += "filter  movies - to filter the list of movies\n";
        this.commandsMenu += "filter  plays in - to filter the list of plays in relations\n";
        this.commandsMenu += "filter  directors - to filter the list of directors\n";

        // report user commands
        this.commandsMenu += "report actors - to find how much percent of actors have a fame above 10\n";
        this.commandsMenu += "report movies - to find how much percent of movies have appeared this decade\n";
        this.commandsMenu += "report plays in - to find how much percent of actors have a specific role in movies\n";
        this.commandsMenu += "report directors - to find how much percent of directors are young\n";

    }

    private void reportDirectors() {
        Long percentage = this.controller.getPercentageOfYoungDirectors();
        System.out.println(percentage + "% of directors are young");
    }

    private void reportMovies() {
        Long percentage = this.controller.getPercentageOfMoviesThisDecade();
        System.out.println(percentage + "% of movies appeared this decade");
    }

    private void reportActors() {
        Long percentage = this.controller.getPercentageOfFamousActors();
        System.out.println(percentage + "% of actors are famous");
    }

    private void reportPlaysIn() {
        try{
            var role = this.getField("Role:");
            Long percent = this.controller.getPercentageOfRolesOfActors(role);
            System.out.println(percent + "% of actors have " + role + " as their role");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterDirector() {
        System.out.println("Here you can see the directors younger then an inputted age");
        try {
            var age = Integer.parseInt(this.getField("Age:"));
            Set<Director> directors = this.controller.getDirectorsWithAgeSmallerThen(age);
            directors.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterDirector();
        }
    }

    private void filterPlaysIn() {
        System.out.println("Here you can see the relations of movies and actors based on the roles");
        try {
            var role = this.getField("Role:");
            Set<PlaysIn> playsIns = this.controller.getPlayInRelationAfterRole(role);
            playsIns.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterPlaysIn();
        }
    }

    private void filterMovie() {
        System.out.println("Here you can see the movies that have a rating greater then the one you insert");
        try {
            var rating = Integer.parseInt(this.getField("Rating:"));
            Set<Movie> movies = this.controller.getMoviesWithRatingHigherThan(rating);
            movies.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterMovie();
        }
    }

    private void filterActor() {
        System.out.println("Here you can see the actors that have a fame in a specific interval");
        try {
            var low = Integer.parseInt(this.getField("Low value:"));
            var high = Integer.parseInt(this.getField("High value:"));
            Set<Actor> actors = this.controller.getActorsWithFameBetween(low, high);
            actors.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.filterActor();
        }
    }

    private void updateDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            var name = this.getField("Name:");
            var age = Integer.parseInt(this.getField("Age:"));
            this.controller.updateDirector(id, name, age);

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
            this.controller.updatePlaysIn(movieID, actorID, role);

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
            this.controller.updateMovie(id, name, rating, year, directorID);

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
            this.controller.updateActor(id, name, age, fame);

        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.updateActor();
        }
    }

    private void deleteDirector() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.controller.deleteDirector(id);
        } catch (IOException | InexistentEntity e ) {
            System.out.println(e.getMessage());
            this.deleteDirector();
        }
    }

    private void deletePlaysIn() {
        try {
            var movieID = Integer.parseInt(this.getField("Movie ID:"));
            var actorID = Integer.parseInt(this.getField("Actor ID:"));
            this.controller.deletePlaysIn(movieID, actorID);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deletePlaysIn();
        }
    }

    private void deleteMovie() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.controller.deleteMovie(id);
        } catch (IOException | InexistentEntity e) {
            System.out.println(e.getMessage());
            this.deleteMovie();
        }
    }

    private void deleteActor() {
        try {
            var id = Integer.parseInt(this.getField("ID:"));
            this.controller.deleteActor(id);
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
            this.controller.addDirector(id, name, age);
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
            this.controller.addMovie(id, name, rating, year, directorId);
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
            this.controller.addActor(id, name, age, fame);
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

            this.controller.addPlaysIn(movieId, actorId, role);
        } catch (IOException | ValidException | DuplicateException e ) {
            System.out.println(e.getMessage());
            this.addPlaysIn();
        }
    }

    public void showMovies() {
        this.controller.getAllMovies().forEach(movie -> System.out.println(movie.toString()));
    }

    public void showActors() {
        this.controller.getAllActors().forEach(actor -> System.out.println(actor.toString()));
    }

    public void showPlaysIn() {
        this.controller.getAllPlaysIn().forEach(playsIn -> System.out.println(playsIn.toString()));
    }

    private void showDirectors() {
        this.controller.getAllDirectors().forEach(director -> System.out.println(director.toString()));
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

}
