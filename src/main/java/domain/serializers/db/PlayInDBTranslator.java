package domain.serializers.db;

import domain.PlaysIn;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayInDBTranslator implements IDBTranslator<PlaysIn> {
    @Override
    public String serializer(PlaysIn entity) {
        return "(" + entity.toDBValues() + ")";
    }

    @Override
    public PlaysIn deserializer(ResultSet rs) throws SQLException {
        int movieId = rs.getInt("movieid");
        int actorId = rs.getInt("actorid");
        String role = rs.getString("role");

        return new PlaysIn(movieId, actorId, role);
    }

    @Override
    public String getTable() {
        return "PlayIn";
    }

    @Override
    public String getFields() {
        return "(movieId, actorId, role)";
    }

    @Override
    public String searchById() {
        return "WHERE (movieId, actorId) = ";
    }
}
