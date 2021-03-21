package networking.Utils;

import domain.serializers.*;
import exception.InexistentEntity;
import networking.Message;
import networking.primitevesSerialization.IntegerSerializer;
import networking.primitevesSerialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

import domain.*;

public class NetworkUtils {
    public static Map<Class<?>, ICSVSerializer<?>> serializers = new HashMap<>();

    static {
        serializers.put(int.class, new IntegerSerializer());
        serializers.put(Integer.class, new IntegerSerializer());
        serializers.put(String.class, new StringSerializer());
        serializers.put(Actor.class, new ActorCSVSerializer());
        serializers.put(Director.class, new DirectorCSVSerializer());
        serializers.put(Movie.class, new MovieCSVSerializer());
        serializers.put(PlaysIn.class, new PlaysInSerializer());
    }

    private NetworkUtils(){}

    public static Message createExceptionMessage(String exceptionText){
        return new Message("exception", exceptionText);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializeObject(String string, Class<T> _class){
        if(!serializers.containsKey(_class))
            throw new InexistentEntity("Class can not be deserialized");
        return (T) serializers.get(_class).deserialize(string);
    }
    @SuppressWarnings({"unchecked"})
    public static <T> String serialiseObject(T object){
        var _class = object.getClass();
        if(!serializers.containsKey(_class))
            throw new InexistentEntity("Class can not be serialised");
        ICSVSerializer<T> _serializer = (ICSVSerializer<T>) serializers.get(_class);
        return _serializer.serialize(object);
    }

}
