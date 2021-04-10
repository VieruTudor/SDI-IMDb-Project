package model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Movie extends BaseEntity<Integer>{
    private String name;
    private int rating;
    private int year;
    private int directorId;

    @Override
    public String toString() {
        return this.toCSV();
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s", this.id, this.name, this.rating, this.year, this.directorId);
    }

    @Override
    public String toDBValues(){
        String values;
        values = String.format("%d, '%s', %d, %d, %d", this.id, this.name, this.rating, this.year, this.directorId);
        return values;
    }
}
