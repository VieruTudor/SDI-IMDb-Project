package interfaces;

import domain.Actor;

public interface IActorController {
    void addActor(int id, String name, int age, int fame);

    void deleteActor(int id);

    void updateActor(int id, String name, int age, int fame);

    Iterable<Actor> getAllActors();

    Iterable<Actor> getActorsWithFameBetween(int lower, int upper);

    Double getPercentageOfFamousActors(int fame);


}
