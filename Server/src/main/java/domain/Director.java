package domain;

import javax.persistence.Entity;

@Entity
public class Director extends BaseEntity<Integer> {
    private String name;
    private int age;

    /**
     * Creates an object of type Director
     *
     * @param name the name of the Director
     * @param age  the age of the Director
     */
    public Director(){

    }
    public Director(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Gets the name of a certain Director
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name to a certain Director
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of a certain Director
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets a new age to a certain Director
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

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
