package domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PlaysIn extends BaseEntity<Pair<Integer, Integer>>{
    private int movieID;
    private int actorID;
    private String role;

    public PlaysIn(){

    }
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
        this.setId(new Pair<>(movieID, actorID));
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaysIn playsIn = (PlaysIn) o;
        return movieID == playsIn.movieID &&
                actorID == playsIn.actorID &&
                Objects.equals(role, playsIn.role);
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
