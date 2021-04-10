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

}
