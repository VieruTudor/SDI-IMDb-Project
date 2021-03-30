package database.adapters;

import domain.Movie;
import domain.Pair;
import domain.PlaysIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class PlaysInTableAdapter implements TableAdapter<Pair<Integer, Integer>, PlaysIn> {

    @Autowired
    private JdbcOperations jdbcOperations;

    private RowMapper<PlaysIn> mapper = (resultSet, rowNumber) ->
    {
        PlaysIn playsIn = new PlaysIn(
                resultSet.getInt("movieid"),
                resultSet.getInt("directorid"),
                resultSet.getString("role")
        );
        return playsIn;
    };

    @Override
    public void insert(PlaysIn entity) {
        String query = "INSERT INTO playin VALUES(?,?,?)";
        jdbcOperations.update(
                query,
                entity.getId().getFirst(),
                entity.getId().getSecond(),
                entity.getRole()
        );
    }

    @Override
    public List<PlaysIn> readAll() {
        String query = "SELECT * FROM playin";
        return jdbcOperations.query(query,mapper);
    }

    @Override
    public Optional<PlaysIn> read(Pair<Integer, Integer> id) {
        String query = "SELECT * FROM playin WHERE id = ?";
        List<PlaysIn> playsIns = jdbcOperations.query(query,
                new Object[]{id},
                mapper);
        if(playsIns.size() != 1){
            return Optional.empty();
        }
        return Optional.of(playsIns.get(0));
    }

    @Override
    public void update(PlaysIn entity) {
        String query = "UPDATE playin SET movieid = ?, actorid = ?, role = ? WHERE id = ?";
        jdbcOperations.update(query, entity.getId().getFirst(), entity.getId().getSecond(), entity.getRole());
    }

    @Override
    public void delete(Pair<Integer, Integer> id) {
        String query = "DELETE FROM playsin WHERE id = ?";
        jdbcOperations.update(query, id);
    }
}
