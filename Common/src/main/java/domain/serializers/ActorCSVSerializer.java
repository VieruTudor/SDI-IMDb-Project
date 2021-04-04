package domain.serializers;

import domain.Actor;

import java.util.Optional;

public class ActorCSVSerializer implements ICSVSerializer<Actor> {
    @Override
    public String serialize(Actor actor) {
        return actor.toCSV();
    }

    @Override
    public Actor deserialize(String string) throws Exception {
        var fields = string.split(",");

        Optional.of(fields)
                .filter(field -> field.length == 4)
                .orElseThrow(() -> new Exception("Actor CSV string cannot be parsed"));
        var id = Integer.parseInt(fields[0]);
        var name = fields[1];
        var age = Integer.parseInt(fields[2]);
        var fame = Integer.parseInt(fields[3]);

        Actor readActor = new Actor(name, age, fame);
        readActor.setId(id);
        return readActor;

    }
}
