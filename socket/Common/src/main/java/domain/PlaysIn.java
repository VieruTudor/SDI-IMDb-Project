package domain;

import java.util.*;

// TODO ALIN

public class PlaysIn extends BaseEntity<Pair<Integer, Integer>> {
    private final int movieID;
    private final int actorID;
    private final String role;

    /**
     * Creates a PlaysIn objiect with the given ids of an actor and movie, meaning that the actors plays in that movie
     *
     * @param movieID Id of an existing movie
     * @param actorID Id of an existing actor
     * @param role    the role of the actor in that movie
     */
    public PlaysIn(int movieID, Integer actorID, String role) {
        this.movieID = movieID;
        this.actorID = actorID;
        this.role = role;
        this.setId(new Pair<>(actorID, movieID));
    }


    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return this.role;
    }

    @Override
    public boolean equals(Object o) {
        PlaysIn playsIn = (PlaysIn) (o);
        return this.getId().getFirst().equals(playsIn.getId().getFirst()) && this.getId().getSecond().equals(playsIn.getId().getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID, actorID, role);
    }

    @Override
    public String toString() {
        return this.toCSV();
    }

    @Override
    public String toCSV(){
        return String.format("%s, %s, %s", this.movieID, this.actorID, this.role);

    }

    @Override
    public String toDBValues(){
        String values;
        values = String.format("%d, %d, '%s'", this.movieID, this.actorID, this.role);
        return values;
    }
}
