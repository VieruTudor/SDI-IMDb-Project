package Controllers;
import controller.IActorController;
import repository.*;
import domain.*;
import java.util.stream.*;
import java.util.Set;
import Validators.Validator;
public class ActorController  implements IActorController{
    private IRepository<Integer, Actor> actors;
    public ActorController(IRepository<Integer,Actor> repo)
    {
        this.actors=repo;
    }
    /**
     * Creates an actor object from the received parameters and adds it to the repository.
     *
     * @param id   given ID
     * @param name given Name
     * @param age  given Age
     * @param fame given Fame
     */
    @Override
    public void addActor(int id, String name, int age, int fame)
    {
        Validator.validateActor(name,age,fame);
        Actor newActor=new Actor(name,age,fame);
        newActor.setId(id);
        this.actors.save(newActor);
    }
    /**
     * Deletes an actor based on its id
     *
     * @param id given Id for the actor to be deleted
     */
    @Override
    public void deleteActor(int id)
    {
        this.actors.delete(id);
    }
    /**
     * Updates an actor based on a given ID
     *
     * @param id   the given ID
     * @param name the new name
     * @param age  the new age
     * @param fame the new fame
     */
    @Override
    public void updateActor(int id, String name, int age, int fame)
    {
        Validator.validateActor(name,age,fame);
        Actor new_actor = new Actor(name, age, fame);
        new_actor.setId(id);
        this.actors.update(new_actor);
    }
    /**
     * Gets all the actors in the repository.
     *
     * @return Iterable containing all actors in the repository.
     */
    @Override
    public Iterable<Actor> getAllActors()
    {
        return this.actors.findAll();
    }
    /**
     * Get all actors with fame between given interval
     *
     * @param lower - lower threshold of the interval
     * @param upper - upper threshold of the interval
     * @return Set containing the collection resulted
     */
    @Override
    public Set<Actor> getActorsWithFameBetween(int lower, int upper)
    {
        return StreamSupport.stream(this.getAllActors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getFame() >= lower && m.getFame() <= upper).collect(Collectors.toSet());
    }
    /**
     * Get percentage of actors that have a fame greater then 60
     *
     * @return Long value representing the requested percentage
     */
    @Override
    public Long getPercentageOfFamousActors()
    {
        long famousActors = StreamSupport.stream(this.getAllActors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(a -> a.getFame() >= 6)
                .count();
        long allActors = StreamSupport.stream(this.getAllActors().spliterator(), false).count();
        return (famousActors * 100) / allActors;
    }
}
