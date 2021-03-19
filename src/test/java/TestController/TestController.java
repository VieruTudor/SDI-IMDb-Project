package TestController;

import controller.Controller;
import domain.*;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import java.util.ArrayList;

public class TestController {
    /// TODO : Implement Controller methods with @Test (NON STATIC METHODS)

    @Test
    public void testAddMovie() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 30);
        controller.addMovie(119, "Ionut", 5, 1900, 2);
        assert (movies.length() == 1);

    }

    @Test
    public void testAddActor() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);
        controller.addActor(133, "Gioni", 23, 8);
        assert (actors.length() == 1);
    }

    @Test
    public void testAddPlaysIn() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 39);
        controller.addMovie(219, "Gucci", 5, 1999, 2);
        controller.addActor(188, "GucciActor", 30, 8);
        controller.addPlaysIn(219, 188, "Main role");
        assert (playsIn.length() == 1);
        assert (playsIn.findOne(new Pair<>(188, 219)).isPresent());
        assert (playsIn.findOne(new Pair<>(188, 219)).get().getRole().equals("Main role"));

    }

    @Test
    public void testAddDirector() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);
        controller.addDirector(2, "Steve", 44);
        assert (directors.findOne(2).isPresent());
        assert (directors.findOne(2).get().getName().equals("Steve"));
    }

    @Test
    public void testGetAllMovies() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 30);
        controller.addMovie(300, "Marcel", 2, 2003, 2);
        var movies_added = (ArrayList<Movie>) controller.getAllMovies();
        assert (movies_added.size() == 1);
    }

    @Test
    public void testGetAllActors() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);
        controller.addActor(555, "Porumb", 60, 9);

        var actors_added = (ArrayList<Actor>) controller.getAllActors();
        assert (actors_added.size() == 1);
    }

    @Test
    public void testGetAllPlaysIn() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 39);
        controller.addMovie(219, "Gucci", 5, 1999, 2);
        controller.addActor(188, "GucciActor", 30, 8);
        controller.addPlaysIn(219, 188, "Main role");

        var playsIn_added = (ArrayList<PlaysIn>) controller.getAllPlaysIn();
        assert (playsIn_added.size() == 1);
    }

    @Test
    public void testGetAllDirectors() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);
        controller.addDirector(2, "Colo", 22);
        var addedDirector = (ArrayList<Director>) controller.getAllDirectors();
        assert (addedDirector.size() == 1);
    }

    @Test
    public void testUpdateActor() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addActor(1, "testActor", 35, 10);
        controller.updateActor(1, "newTestActor", 36, 15);
        assert (actors.findOne(1).isPresent());
        assert (actors.findOne(1).get().getName().equals("newTestActor"));
    }

    @Test
    public void testUpdateMovie() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 30);
        assert (directors.findOne(2).isPresent());
        assert (directors.findOne(2).get().getName().equals("Hawk"));

        controller.addMovie(119, "Ionut", 5, 1900, 2);
        controller.updateMovie(119, "newTestMovie", 40, 2015, 2);

        assert (movies.findOne(119).isPresent());
        assert (movies.findOne(119).get().getName().equals("newTestMovie"));
    }

    @Test
    public void testUpdateDirector() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(2, "Hawk", 30);

        assert (directors.findOne(2).isPresent());
        assert (directors.findOne(2).get().getName().equals("Hawk"));

        controller.updateDirector(2, "Hawking", 39);

        assert (directors.findOne(2).isPresent());
        assert (directors.findOne(2).get().getName().equals("Hawking"));
    }

    @Test
    public void testUpdatePlaysIn() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);
        controller.addMovie(5, "TestMovie", 50, 2000, 1);
        controller.addActor(1, "TestActor", 30, 50);
        controller.addPlaysIn(5, 1, "main role");

        Pair<Integer, Integer> playsInPair = new Pair<>(1, 5);

        assert (playsIn.findOne(playsInPair).isPresent());
        assert (playsIn.findOne(playsInPair).get().getRole().equals("main role"));

        controller.updatePlaysIn(5, 1, "secondary role");

        assert (playsIn.findOne(playsInPair).isPresent());
        assert (playsIn.findOne(playsInPair).get().getRole().equals("secondary role"));
    }

    @Test
    public void testDeleteActor() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addActor(1, "Test Actor", 30, 30);
        controller.addActor(2, "Test Actor 2", 35, 30);

        assert (actors.length() == 2);

        controller.deleteActor(1);

        assert actors.findOne(1).isEmpty();

    }

    @Test
    public void testDeleteMovie() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);
        controller.addMovie(1, "Movie 1", 30, 2015, 1);
        controller.addMovie(2, "Movie 2", 45, 2016, 1);
        controller.addMovie(3, "Movie 3", 50, 2018, 1);

        assert (movies.length() == 3);

        controller.deleteMovie(2);

        assert movies.findOne(2).isEmpty();
    }

    @Test
    public void testDeletePlaysIn() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);

        controller.addMovie(1, "Movie", 30, 2015, 1);
        controller.addMovie(2, "Movie 2", 50, 2020, 1);

        controller.addActor(1, "Actor", 30, 20);
        controller.addActor(2, "Actor 2", 30, 50);

        controller.addPlaysIn(1, 1, "main role");
        controller.addPlaysIn(1, 2, "secondary role");
        controller.addPlaysIn(2, 2, "main role");

        assert (playsIn.length() == 3);

        controller.deletePlaysIn(1, 2);

        assert (playsIn.findOne(new Pair<>(2, 1)).isEmpty());
    }

    @Test
    public void testDeleteDirector() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);
        controller.addDirector(2, "Hawk 2", 30);
        controller.addDirector(3, "Hawk 3", 30);

        assert (directors.length() == 3);

        controller.deleteDirector(2);

        assert (directors.findOne(2).isEmpty());
    }

    @Test
    public void testGetMoviesWithRatingHigherThen() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);
        controller.addMovie(1, "Movie 1", 30, 2015, 1);
        controller.addMovie(2, "Movie 2", 20, 2016, 1);
        controller.addMovie(3, "Movie 3", 21, 2012, 1);
        controller.addMovie(4, "Movie 4", 25, 2012, 1);
        controller.addMovie(5, "Movie 5", 10, 2012, 1);

        assert (movies.length() == 5);

        var filteredMovies = controller.getMoviesWithRatingHigherThan(21);

        assert (filteredMovies.size() == 3);
    }

    @Test
    public void testGetActorsWithFameBetween() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addActor(1, "Actor 1", 30, 30);
        controller.addActor(2, "Actor 2", 20, 35);
        controller.addActor(3, "Actor 3", 40, 20);
        controller.addActor(4, "Actor 4", 35, 50);
        controller.addActor(5, "Actor 5", 35, 100);

        var filteredActors = controller.getActorsWithFameBetween(30, 50);

        assert (filteredActors.size() == 3);
    }

    @Test
    public void testGetDirectorsWithAgeSmallerThen() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Director 1", 30);
        controller.addDirector(2, "Director 2", 40);
        controller.addDirector(3, "Director 3", 25);
        controller.addDirector(4, "Director 4", 31);
        controller.addDirector(5, "Director 5", 15);

        var filteredDirectors = controller.getDirectorsWithAgeSmallerThen(30);

        assert (filteredDirectors.size() == 3);

    }

    @Test
    public void testGetPlayInRelationAfterRole() {
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);

        controller.addMovie(1, "Movie", 30, 2015, 1);
        controller.addMovie(2, "Movie 2", 50, 2020, 1);

        controller.addActor(1, "Actor", 30, 20);
        controller.addActor(2, "Actor 2", 30, 50);

        controller.addPlaysIn(1, 1, "main role");
        controller.addPlaysIn(1, 2, "secondary role");
        controller.addPlaysIn(2, 2, "main role");

        var getFilteredPlaysIn = controller.getPlayInRelationAfterRole("main role");

        assert (getFilteredPlaysIn.size() == 2);

    }

    @Test
    public void testGetPercentageOfRolesOfActors(){
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);

        controller.addMovie(1, "Movie", 30, 2015, 1);
        controller.addMovie(2, "Movie 2", 50, 2020, 1);

        controller.addActor(1, "Actor", 30, 20);
        controller.addActor(2, "Actor 2", 30, 50);

        controller.addPlaysIn(1, 1, "lead");
        controller.addPlaysIn(1, 2, "secondary");

        var getPercentage = controller.getPercentageOfRolesOfActors("lead");

        assert (getPercentage == 50);

    }

    @Test
    public void testGetPercentageOfFamousActors(){
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addActor(1, "Actor", 30, 5);
        controller.addActor(2, "Actor 2", 30, 50);

        var getPercentage = controller.getPercentageOfFamousActors();

        assert (getPercentage == 50);

    }

    @Test
    public void testGetPercentageOfMoviesThisDecade(){
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Hawk", 30);

        controller.addMovie(1, "Movie", 30, 2005, 1);
        controller.addMovie(2, "Movie 2", 50, 2020, 1);

        var getPercentage = controller.getPercentageOfMoviesThisDecade();

        assert (getPercentage == 50);
    }

    @Test
    public void testGetPercentageOfYoungDirectors(){
        MemoryRepository<Integer, Movie> movies = new MemoryRepository<>();
        MemoryRepository<Integer, Actor> actors = new MemoryRepository<>();
        MemoryRepository<Pair<Integer, Integer>, PlaysIn> playsIn = new MemoryRepository<>();
        MemoryRepository<Integer, Director> directors = new MemoryRepository<>();
        Controller controller = new Controller(actors, movies, playsIn, directors);

        controller.addDirector(1, "Director 1", 25);
        controller.addDirector(2, "Director 2", 27);
        controller.addDirector(4, "Director 4", 31);
        controller.addDirector(5, "Director 5", 45);

        var getPercentage = controller.getPercentageOfYoungDirectors();

        assert (getPercentage == 50);
    }

}
