package domain;

// TODO: Tudor

import java.io.Serializable;
import java.util.Objects;

public class Actor extends BaseEntity<Integer> implements Serializable {


    private String name;
    private int age;
    private int fame;

    public Actor(String name, int age, int fame) {
        this.name = name;
        this.age = age;
        this.fame = fame;
    }


    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Gets the fame.
     *
     * @return the fame
     */
    public int getFame() {
        return this.fame;
    }

    /**
     * Sets the name to the given parameter
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the age to the given parameter
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the fame to the given parameter
     *
     * @param fame the new fame
     */
    public void setFame(int fame) {
        this.fame = fame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return age == actor.age &&
                fame == actor.fame &&
                Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, fame);
    }

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
