package database.serializers;

import domain.Director;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorDBTranslator implements IDBTranslator<Director> {
    @Override
    public String serializer(Director entity) {
        return "(" + entity.toDBValues() + ")";
    }

    @Override
    public Director deserializer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");

        var director = new Director(name, age);
        director.setId(id);
        return director;
    }

    @Override
    public String getTable() {
        return "director";
    }

    @Override
    public String getFields() {
        return "(id, name, age)";
    }

    @Override
    public String searchById() {
        return "Where id = ";
    }
}
