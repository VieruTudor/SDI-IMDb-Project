package model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Director extends BaseEntity<Integer>{
    private String name;
    private int age;

    @Override
    public String toString() {
        return this.toCSV();
    }

    @Override
    public String toCSV(){
        return String.format("%s,%s,%s", this.id, this.name, this.age);
    }

    @Override
    public String toDBValues(){
        String values;
        values = String.format("%d, '%s', %d", this.id, this.name, this.age);
        return values;
    }
}
