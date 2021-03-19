package TestDomain;

import domain.Actor;
import org.junit.jupiter.api.Test;

public class TestActor {

    @Test
    public void testGetName() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getName().equals("Test Actor"));
    }

    @Test
    public void testGetAge() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getAge() == 30);
    }

    @Test
    public void testGetFame() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getFame() == 5);
    }

    @Test
    public void testSetName() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getName().equals("Test Actor"));
        testActor.setName("Test Actor 2");
        assert (testActor.getName().equals("Test Actor 2"));
    }

    @Test
    public void testSetAge() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getAge() == 30);
        testActor.setAge(50);
        assert (testActor.getAge() == 50);
    }

    @Test
    public void testSetFame() {
        Actor testActor = new Actor("Test Actor", 30, 5);
        assert (testActor.getFame() == 5);
        testActor.setFame(10);
        assert (testActor.getFame() == 10);
    }

}
