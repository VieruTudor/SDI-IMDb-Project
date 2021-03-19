package TestDomain;

import domain.Movie;
import org.junit.jupiter.api.Test;

public class TestMovie {


    @Test
    public void testGetName() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        assert (movie.getName().equals("Test"));
    }

    @Test
    public void testGetRating() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        assert (movie.getRating() == 78);
    }

    @Test
    public void testGetYear() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        assert (movie.getYear() == 2015);
    }

    @Test
    public void testGetDirectorId() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        assert (movie.getDirectorId() == 2);
    }

    @Test
    public void testSetName() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        movie.setName("Test2");
        assert (movie.getName().equals("Test2"));
    }

    @Test
    public void testSetRating() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        movie.setRating(89);
        assert (movie.getRating() == 89);
    }

    @Test
    public void testSetYear() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        movie.setYear(2020);
        assert (movie.getYear() == 2020);
    }

    @Test
    public void testSetDirectorId() {
        Movie movie = new Movie("Test", 78, 2015, 2);
        movie.setDirectorId(3);
        assert (movie.getDirectorId() == 3);
    }

}