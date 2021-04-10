package validators;
import exceptions.ValidException;

public class Validator {

    /**
     * Validates the movie
     * @param name of the movie
     * @param rating of the movie
     * @param year of the movie
     * @throws ValidException if the entity is not valid meaning: it has empty name, rating outside [0, 100] or negative year
     */
    public static void validateMovie(String name, int rating, int year, int directorId) throws ValidException {
        if(name.equals("")){
            throw new ValidException("The name of the movie should not be an empty string!");
        }
        if(rating < 0 || rating > 100){
            throw new ValidException("The rating of the movie should be between 0 an 100!");
        }
        if(year < 0){
            throw new ValidException("Year should be greater than 0!");
        }
        if (directorId < 0){
            throw new ValidException("The ID of the director can't be a negative number");
        }
    }

    /**
     * Validates the actor.
     * @param name of the actor
     * @param age of the actor
     * @param fame of the actor
     * @throws ValidException if the entity is not valid meaning: it has empty name, negative age or negative fame.
     */
    public static void validateActor(String name, int age, int fame) throws ValidException{
        if(name.equals("")){
            throw new ValidException("The name of the actor should not be an empty string!");
        }
        if(age < 0){
            throw new ValidException("The age of the actor should not be less than 0!");
        }
        if(fame < 0){
            throw new ValidException("The fame of the actor should be greater than 0!");
        }
    }

    /**
     * Validates the PlaysIn object.
     * @param role of the PlaysIn
     * @throws ValidException if the entity is not valid meaning: it has empty name.
     */
    public static void validatePlaysIn(String role) throws ValidException{
        if(role.equals("")){
            throw new ValidException("The role should not be an empty string!");
        }
    }

    /**
     * Validates a Director object
     * @param directorName name of the Director
     * @param directorAge age of the Director
     * @throws ValidException if the entity is not valid: having no name or an age less or equal to 0
     */
    public static void validateDirector(String directorName, int directorAge) {
        if(directorName.equals("")){
            throw new ValidException("Director's name can't be an empty string!");
        }
        if(directorAge <= 0){
            throw new ValidException("Director's age must be greater than 0!");
        }
    }
}