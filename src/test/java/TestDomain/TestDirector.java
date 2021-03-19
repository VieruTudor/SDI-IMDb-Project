package TestDomain;

import domain.Director;
import org.junit.jupiter.api.Test;

public class TestDirector {

    @Test
    public void testGetName() {
        Director director = new Director("Hawk", 39);
        assert (director.getName().equals("Hawk"));
    }

    @Test
    public void testGetAge() {
        Director director = new Director("Hawk", 39);
        assert (director.getAge() == 39);
    }

    @Test
    public void testSetName() {
        Director director = new Director("Hawk", 39);
        assert (director.getName().equals("Hawk"));
        director.setName("Steve");
        assert (director.getName().equals("Steve"));
    }

    @Test
    public void testSetAge() {
        Director director = new Director("Hawk", 39);
        assert (director.getAge() == 39);
        director.setAge(41);
        assert (director.getAge() == 41);
    }


}
