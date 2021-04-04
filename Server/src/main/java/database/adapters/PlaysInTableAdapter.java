package database.adapters;

import database.Database;
import domain.Movie;
import domain.Pair;
import domain.PlaysIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class PlaysInTableAdapter implements TableAdapter<Pair<Integer, Integer>, PlaysIn> {

    @Autowired
    private JdbcOperations jdbcOperations;

    private RowMapper<PlaysIn> mapper = (resultSet, rowNumber) ->
    {
        var movieid = resultSet.getInt("movieid");
        var actorid = resultSet.getInt("actorid");
        var role = resultSet.getString("role");
        PlaysIn playsIn = new PlaysIn(
                movieid,
                actorid,
                role
        );
        playsIn.setId(new Pair<>(movieid, actorid));
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
        String query = String.format("SELECT * FROM playin WHERE movieid = %d AND actorid = %d", id.getFirst(), id.getSecond());
        List<PlaysIn> playsIns = jdbcOperations.query(query,
                mapper);
        if(playsIns.size() != 1){
            return Optional.empty();
        }
        return Optional.of(playsIns.get(0));
    }

    @Override
    public void update(PlaysIn entity) {
        String query = "UPDATE playin SET role = ? WHERE movieid = ? AND actorid = ?";
        jdbcOperations.update(query, entity.getRole(), entity.getId().getFirst(), entity.getId().getSecond());
    }

    @Override
    public void delete(Pair<Integer, Integer> id) {
        String query = "DELETE FROM playin WHERE movieid = ? AND actorid = ?";
        jdbcOperations.update(query, id.getFirst(), id.getSecond());
    }
}
