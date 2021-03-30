package database.adapters;

import domain.Actor;
import domain.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class DirectorTableAdapter implements TableAdapter<Integer, Director> {

    @Autowired
    private JdbcOperations jdbcOperations;

    private RowMapper<Director> mapper = (resultSet, rowNumber) ->
    {
        Integer directorId = resultSet.getInt("id");
        Director director = new Director(
                resultSet.getString("name"),
                resultSet.getInt("age")
        );
        director.setId(directorId);
        return director;
    };
    @Override
    public void insert(Director entity) {
        String query = "INSERT INTO actor VALUES(?,?,?)";
        jdbcOperations.update(
                query,
                entity.getId(),
                entity.getName(),
                entity.getAge()
        );
    }

    @Override
    public List<Director> readAll() {
        String query = "SELECT * FROM director";
        return jdbcOperations.query(query,mapper);
    }

    @Override
    public Optional<Director> read(Integer id) {
        String query = "SELECT * FROM director WHERE id = ?";
        List<Director> directors = jdbcOperations.query(query,
                new Object[]{id},
                mapper);
        if(directors.size() != 1){
            return Optional.empty();
        }
        return Optional.of(directors.get(0));
    }

    @Override
    public void update(Director entity) {
        String query = "UPDATE director SET name = ?, age = ? WHERE id = ?";
        jdbcOperations.update(query, entity.getName(), entity.getAge());
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM director WHERE id = ?";
        jdbcOperations.update(query, id);
    }
}
