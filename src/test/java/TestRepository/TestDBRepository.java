package TestRepository;

import domain.*;
import domain.serializers.db.ActorDBTranslator;
import domain.serializers.db.DirectorDBTranslator;
import domain.serializers.db.MovieDBTranslator;
import domain.serializers.db.PlayInDBTranslator;

import org.junit.jupiter.api.Test;
import repository.DBRepository;
import java.util.stream.StreamSupport;

public class TestDBRepository {

    // TEST WORKING ONLY IF DB IS POPULATED WITH THE SCRIPT
    @Test
    public void testLengthActor(){
        DBRepository<Integer, Actor> actors = new DBRepository<>(new ActorDBTranslator());
        assert (actors.length() == 5);
    }
    @Test
    public void testFindOneActor(){
        DBRepository<Integer, Actor> actors = new DBRepository<>(new ActorDBTranslator());
        assert(actors.findOne(1).isPresent());
    }

    @Test
    public void testFindAllActors(){
        DBRepository<Integer, Actor> actors = new DBRepository<>(new ActorDBTranslator());
        assert ( actors.length() == 5 );
    }

    @Test
    public void testSaveActor(){
        DBRepository<Integer, Actor> actors = new DBRepository<>(new ActorDBTranslator());
        var testActor = new Actor("This should not exist", 10, 10);
        testActor.setId(10);
        actors.save(testActor);
        assert (actors.length() == 6 );
    }

    @Test
    public void testDeleteActor(){
        DBRepository<Integer, Actor> actors = new DBRepository<>(new ActorDBTranslator());
        actors.delete(10);
        assert (actors.findOne(10).isEmpty());
    }

    @Test
    public void testLengthMovie(){
        DBRepository<Integer, Movie> movies = new DBRepository<>(new MovieDBTranslator());
        assert (movies.length() == 2);
    }
    @Test
    public void testFindOneMovie(){
        DBRepository<Integer, Movie> movies = new DBRepository<>(new MovieDBTranslator());
        assert(movies.findOne(1).isPresent());
    }

    @Test
    public void testFindAllMovie(){
        DBRepository<Integer, Movie> movies = new DBRepository<>(new MovieDBTranslator());
        assert ( movies.length() == 2 );
    }

    @Test
    public void testSaveMovie(){
        DBRepository<Integer, Movie> movies = new DBRepository<>(new MovieDBTranslator());
        var testActor = new Movie("This should not exist", 10, 10, 1);
        testActor.setId(3);
        movies.save(testActor);
        assert (movies.length() == 3 );
    }

    @Test
    public void testDeleteMovie(){
        DBRepository<Integer, Movie> movies = new DBRepository<>(new MovieDBTranslator());
        movies.delete(3);
        assert (movies.length() == 2);
    }

    @Test
    public void testLengthDirector(){
        DBRepository<Integer, Director> directors = new DBRepository<>(new DirectorDBTranslator());
        assert (directors.length() == 2);
    }
    @Test
    public void testFindOneDirector(){
        DBRepository<Integer, Director> directors = new DBRepository<>(new DirectorDBTranslator());
        assert(directors.findOne(1).isPresent());
    }

    @Test
    public void testSaveDirector(){
        DBRepository<Integer, Director> directors = new DBRepository<>(new DirectorDBTranslator());
        var director = new Director("This should not be here", 10);
        director.setId(10);
        directors.save(director);
        assert(directors.length() == 3);
    }

    @Test
    public void testDeleteDirector(){
        DBRepository<Integer, Director> directors = new DBRepository<>(new DirectorDBTranslator());
        directors.delete(10);
        assert(directors.length() == 2);
    }

    @Test
    public void testLengthPlayIn(){
        DBRepository<Pair<Integer, Integer>, PlaysIn> playIns = new DBRepository<>(new PlayInDBTranslator());
        assert(playIns.length() == 3);
    }

    @Test
    public void testFindOnePlayIn(){
        DBRepository<Pair<Integer, Integer>, PlaysIn> playIns = new DBRepository<>(new PlayInDBTranslator());
        assert(playIns.findOne(new Pair<>(1, 1)).isPresent());
    }

    @Test
    public void testFindAllPlayIn(){
        DBRepository<Pair<Integer, Integer>, PlaysIn> playIns = new DBRepository<>(new PlayInDBTranslator());
        assert(playIns.length() == 3);
    }

    @Test
    public void testSavePlayIn(){
        DBRepository<Pair<Integer, Integer>, PlaysIn> playIns = new DBRepository<>(new PlayInDBTranslator());
        var playIn = new PlaysIn(1, 2, "This should not be here");
        playIns.save(playIn);
        assert(playIns.length() == 4);
    }

    @Test
    public void testDeletePlayIn(){
        DBRepository<Pair<Integer, Integer>, PlaysIn> playIns = new DBRepository<>(new PlayInDBTranslator());
        playIns.delete(new Pair<>(1, 2));
        assert(playIns.length() == 3);
    }

}
