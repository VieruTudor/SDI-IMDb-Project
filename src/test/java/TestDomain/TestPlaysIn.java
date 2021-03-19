package TestDomain;

import domain.Actor;
import domain.Movie;
import domain.PlaysIn;
import org.junit.jupiter.api.Test;

public class TestPlaysIn {

    @Test
    public void testGetRole() {
        Actor testActor = new Actor("virtual actor", 35, 7);
        testActor.setId(1);

        Movie testMovie = new Movie("virtual movie", 8, 2017, 2);
        testMovie.setId(1);

        String role = "main role";
        PlaysIn testRole = new PlaysIn(testMovie.getId(), testActor.getId(), role);
        assert (testRole.getRole().equals("main role"));
    }
}
