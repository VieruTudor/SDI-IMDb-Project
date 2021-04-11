package model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Actor extends BaseEntity<Integer>{
    private String name;
    private int age;
    private int fame;

    @Override
    public String toString() {
        return this.toCSV();
    }

    @Override
    public String toCSV(){
        return String.format("%s,%s,%s,%s", this.id, this.name, this.age, this.fame);
    }

    @Override
    public String toDBValues(){
        String values;
        values = String.format("%d, '%s', %d, %d", this.id, this.name, this.age, this.fame);
        return values;
    }
}
