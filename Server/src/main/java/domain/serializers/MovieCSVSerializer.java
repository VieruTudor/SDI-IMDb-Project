package domain.serializers;

import domain.Movie;

import java.util.Optional;

public class MovieCSVSerializer implements ICSVSerializer<Movie> {
    @Override
    public String serialize(Movie movie) {
        return movie.toCSV();
    }

    @Override
    public Movie deserialize(String string) throws Exception {
        var fields = string.split(",");

        Optional.of(fields)
                .filter(field -> field.length == 5)
                .orElseThrow(() -> new Exception("Movie CSV string cannot be parsed"));

        var id = Integer.parseInt(fields[0]);
        var name = fields[1];
        var year = Integer.parseInt(fields[2]);
        var rating = Integer.parseInt(fields[3]);
        var directorID = Integer.parseInt(fields[4]);

        Movie readMovie = new Movie(name, year, rating, directorID);
        readMovie.setId(id);

        return readMovie;
    }
}
