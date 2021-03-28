package domain;

public class BaseEntity<ID> {
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