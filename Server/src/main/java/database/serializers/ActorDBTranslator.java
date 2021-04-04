package database.serializers;

import domain.Actor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorDBTranslator implements IDBTranslator<Actor> {

    @Override
    public String serializer(Actor entity) {
        return "(" + entity.toDBValues() + ")";
    }

    @Override
    public Actor deserializer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        int fame = rs.getInt("fame");

        Actor newActor = new Actor(name, age, fame);
        newActor.setId(id);
        return newActor;
    }

    @Override
    public String getTable() {
        return "actor";
    }

    @Override
    public String getFields() {
        return "(id, name, age, fame)";
    }

    @Override
    public String searchById() {
        return "Where id = ";
    }
}
