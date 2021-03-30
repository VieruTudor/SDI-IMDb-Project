package database.adapters;

import config.JdbcConfig;
import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class ActorTableAdapter implements TableAdapter<Integer, Actor> {

    @Autowired
    private JdbcOperations jdbcOperations;


    private RowMapper<Actor> mapper = (resultSet, rowNumber) ->
    {
        Integer actorId = resultSet.getInt("id");
        Actor actor = new Actor(
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("fame")
        );
        actor.setId(actorId);
        return actor;
    };

    @Override
    public void insert(Actor entity) {
        String query = "INSERT INTO actor VALUES(?,?,?,?)";
        jdbcOperations.update(
                query,
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getFame()
        );
    }

    @Override
    public List<Actor> readAll() {
        String query = "SELECT * FROM actor";
        return jdbcOperations.query(query,mapper);
    }

    @Override
    public Optional<Actor> read(Integer id) {
        String query = "SELECT * FROM actor WHERE id = ?";
        List<Actor> actors = jdbcOperations.query(query,
                new Object[]{id},
                mapper);
        if(actors.size() != 1){
            return Optional.empty();
        }
        return Optional.of(actors.get(0));
    }

    @Override
    public void update(Actor entity) {
        String query = "UPDATE actor SET name = ?, age = ?, fame = ? WHERE id = ?";
        jdbcOperations.update(query, entity.getName(), entity.getAge(), entity.getFame());
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM actor WHERE id = ?";
        jdbcOperations.update(query, id);
    }
}
