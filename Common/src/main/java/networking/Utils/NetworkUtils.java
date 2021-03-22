package networking.Utils;

import domain.serializers.*;
import exception.InexistentEntity;
import networking.Message;
import networking.primitevesSerialization.DoubleSerializer;
import networking.primitevesSerialization.IntegerSerializer;
import networking.primitevesSerialization.StringSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.*;

public class NetworkUtils {
    public static Map<Class<?>, ICSVSerializer<?>> serializers = new HashMap<>();

    static {
        serializers.put(int.class, new IntegerSerializer());
        serializers.put(Integer.class, new IntegerSerializer());
        serializers.put(Double.class, new DoubleSerializer());
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

    public static boolean isSuccess(Message message)
    {
        return message.getHeader().equals("success");
    }

    public static void checkException(Message message)
    {
        if (message.getHeader().equals("exception"))
        {
            List<String> messageBody = message.getBody();
            if (messageBody.size() != 1)
            {
                throw new RuntimeException("Received response was invalid");
            }
            throw new RuntimeException(messageBody.get(0));
        }
    }

    public static Message success(List<String> value)
    {
        Message message = new Message("success");
        if (value != null)
            value.forEach(message::addRow);
        return message;
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
