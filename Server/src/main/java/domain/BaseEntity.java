package domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    protected ID id; // id-ul din repo

    /**
     * Gets the id.
     *
     * @return the id
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id of the object.
     */
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public String toCSV(){
        return id.toString();
    }

    public String toDBValues(){
        return id.toString();
    }
}