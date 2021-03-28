package domain;

// TODO: ZAVO
public class Movie extends BaseEntity<Integer> {
    private String name;
    private int rating;
    private int year;
    private int directorId;

    /**
     * Creates a movie object with specific name, rating, year
     *
     * @param name   of the new movie
     * @param rating of the new movie
     * @param year   of the ew movie
     */
    public Movie(String name, int rating, int year, int directorId) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.directorId = directorId;
    }

    /**
     * Gets the name field
     *
     * @return the name of the movie
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the rating field
     *
     * @return the rating of the movie
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Gets the year field
     *
     * @return - <p> the year of the movie </p>
     */
    public int getYear() {
        return this.year;
    }


    public int getDirectorId() {
        return directorId;
    }

    /**
     * Sets the name to the given parameter
     *
     * @param name the new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the rating to the given parameter
     *
     * @param rating the new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Sets the year to the given parameter
     *
     * @param year the new year value
     */
    public void setYear(int year) {
        this.year = year;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

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
