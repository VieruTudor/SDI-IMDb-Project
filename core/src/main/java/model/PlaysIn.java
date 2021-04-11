package model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaysIn extends BaseEntity<Pair<Integer, Integer>>{
    private int movieID;
    private int actorID;
    private String role;

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
