package domain.serializers.db;

import domain.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDBTranslator implements IDBTranslator<Movie> {
    @Override
    public String serializer(Movie entity) {
        return "(" + entity.toDBValues() + ")";
    }

    @Override
    public Movie deserializer(ResultSet rs) throws SQLException {
        int id = rs.getInt("Id");
        String name = rs.getString("name");
        int rating = rs.getInt("rating");
        int year = rs.getInt("year");
        int directorId = rs.getInt("directorId");
        Movie newMovie = new Movie(name, rating, year, directorId);
        newMovie.setId(id);
        return newMovie;
    }

    @Override
    public String getTable() {
        return "movie";
    }

    @Override
    public String getFields() {
        return "(id, name, rating, year, directorId)";
    }

    @Override
    public String searchById() {
        return "Where id = ";
    }

}
