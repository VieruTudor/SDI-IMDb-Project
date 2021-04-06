package database.adapters;

import domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class MovieTableAdapter implements TableAdapter<Integer, Movie>{

    @Autowired
    private JdbcOperations jdbcOperations;

    private RowMapper<Movie> mapper = (resultSet, rowNumber) ->
    {
        Integer movieId = resultSet.getInt("id");
        Movie movie = new Movie(
                resultSet.getString("name"),
                resultSet.getInt("rating"),
                resultSet.getInt("year"),
                resultSet.getInt("directorid")
        );
        movie.setId(movieId);
        return movie;
    };
    @Override
    public void insert(Movie entity) {
        String query = "INSERT INTO movie VALUES(?,?,?,?,?)";
        jdbcOperations.update(
                query,
                entity.getId(),
                entity.getName(),
                entity.getRating(),
                entity.getYear(),
                entity.getDirectorId()
        );
    }

    @Override
    public List<Movie> readAll() {
        String query = "SELECT * FROM movie";
        return jdbcOperations.query(query,mapper);
    }

    @Override
    public Optional<Movie> read(Integer id) {
        String query = "SELECT * FROM movie WHERE id = ?";
        List<Movie> movies = jdbcOperations.query(query,
                new Object[]{id},
                mapper);
        if(movies.size() != 1){
            return Optional.empty();
        }
        return Optional.of(movies.get(0));
    }

    @Override
    public void update(Movie entity) {
        String query = "UPDATE movie SET name = ?, rating = ?, year = ?, directorid = ? WHERE id = ?";
        jdbcOperations.update(query, entity.getName(), entity.getRating(), entity.getYear(), entity.getDirectorId());
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM movie WHERE id = ?";
        jdbcOperations.update(query, id);
    }
}
