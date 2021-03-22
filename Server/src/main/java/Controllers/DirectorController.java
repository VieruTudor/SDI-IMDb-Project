package controllers;

import controller.IDirectorController;
import repository.*;
import domain.*;
import java.util.stream.*;
import java.util.Set;
import Validators.Validator;
public class DirectorController  implements IDirectorController {
    private IRepository<Integer,Director> directors;
    public DirectorController(IRepository<Integer,Director> directors)
    {
        this.directors=directors;
    }
    /**
     * Creates a Director object and adds it into the repository
     *
     * @param id   the Id of the Director
     * @param name name of the Director
     * @param age  age of the Director
     */
    @Override
    public void addDirector(int id, String name, int age)
    {
        Validator.validateDirector(name,age);
        Director newDirector=new Director(name,age);
        newDirector.setId(id);
        this.directors.save(newDirector);
    }
    /**
     * Deletes a Director based on the given Id
     *
     * @param id given Id
     */
    @Override
    public void deleteDirector(int id)
    {
        this.directors.delete(id);
    }
    /**
     * Updates a Director based on a given ID
     *
     * @param id   the given ID
     * @param name the new director name
     * @param age  the new director age
     */
    @Override
    public void updateDirector(int id, String name, int age)
    {
        Validator.validateDirector(name,age);
        Director dir=new Director(name,age);
        dir.setId(id);
        this.directors.update(dir);
    }
    /**
     * Gets all the directors from the repository.
     *
     * @return Iterable containing all the Director objects.
     */
    @Override
    public Iterable<Director> getAllDirectors()
    {
        return this.directors.findAll();
    }
    /**
     * Get all directors with age smaller then given margin
     *
     * @param margin given margin
     * @return Set containing the collection resulted
     */
    @Override
    public Set<Director> getDirectorsWithAgeSmallerThen(int margin) {
        return StreamSupport.stream(this.getAllDirectors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(m -> m.getAge() <= margin).collect(Collectors.toSet());
    }
    /**
     * Get percentage of directors that are younger then 28
     *
     * @return Long value representing the requested percentage
     */
    public Long getPercentageOfYoungDirectors(){
        long youngDirectors = StreamSupport.stream(this.getAllDirectors().spliterator(), false)
                .collect(Collectors.toSet()).stream()
                .filter(director -> director.getAge() <= 28)
                .count();
        long allDirectors = StreamSupport.stream(this.getAllDirectors().spliterator(), false).count();
        return (youngDirectors * 100) / allDirectors;
    }

}
