package TestRepository;

import domain.Actor;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import java.util.ArrayList;

class TestMemoryRepository {

    @Test
    public void testSave() {
        MemoryRepository<Integer, Actor> actorMemoryRepository = new MemoryRepository<>();
        Actor testActor = new Actor("Test Actor", 30, 5);
        testActor.setId(1);
        actorMemoryRepository.save(testActor);
        assert (actorMemoryRepository.length() == 1);
    }

    @Test
    public void testFindOne() {
        MemoryRepository<Integer, Actor> actorMemoryRepository = new MemoryRepository<>();
        Actor testActor = new Actor("Test Actor", 30, 5);
        testActor.setId(1);

        Actor testActor2 = new Actor("Test Actor 2", 35, 10);
        testActor2.setId(2);

        actorMemoryRepository.save(testActor);
        actorMemoryRepository.save(testActor2);


        assert (actorMemoryRepository.findOne(2).isPresent());
        Actor findActor = actorMemoryRepository.findOne(2).get();
        assert (findActor.getId() == 2);


    }

    @Test
    public void testFindAll() {
        MemoryRepository<Integer, Actor> actorMemoryRepository = new MemoryRepository<>();
        Actor testActor = new Actor("Test Actor", 30, 5);
        testActor.setId(1);

        Actor testActor2 = new Actor("Test Actor 2", 35, 10);
        testActor2.setId(2);

        actorMemoryRepository.save(testActor);
        actorMemoryRepository.save(testActor2);

        var iterableRepository = (ArrayList<Actor>) actorMemoryRepository.findAll();
        assert (iterableRepository.get(0).getId() == 1);
        assert (iterableRepository.get(1).getId() == 2);
    }

    @Test
    public void testDelete() {
        MemoryRepository<Integer, Actor> actorMemoryRepository = new MemoryRepository<>();
        Actor testActor = new Actor("Test Actor", 30, 5);
        testActor.setId(1);

        Actor testActor2 = new Actor("Test Actor 2", 35, 10);
        testActor2.setId(2);

        actorMemoryRepository.save(testActor);
        actorMemoryRepository.save(testActor2);

        assert (actorMemoryRepository.length() == 2);

        actorMemoryRepository.delete(1);

        assert (actorMemoryRepository.length() == 1);

        assert (actorMemoryRepository.findOne(1).isEmpty());
    }


    @Test
    public void testUpdate() {
        MemoryRepository<Integer, Actor> actorMemoryRepository = new MemoryRepository<>();
        Actor testActor = new Actor("Test Actor", 30, 5);
        testActor.setId(1);

        Actor testActor2 = new Actor("Test Actor 2", 35, 10);
        testActor2.setId(1);

        actorMemoryRepository.save(testActor);
        actorMemoryRepository.update(testActor2);

        assert (actorMemoryRepository.findOne(1).isPresent());
        assert (actorMemoryRepository.findOne(1).get().getName().equals("Test Actor 2"));

    }
}
