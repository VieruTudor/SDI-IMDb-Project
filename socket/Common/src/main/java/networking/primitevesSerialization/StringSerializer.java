package networking.primitevesSerialization;

import domain.serializers.ICSVSerializer;

public class StringSerializer implements ICSVSerializer<String>
{
    @Override
    public String serialize(String entity) {
        return entity;
    }

    @Override
    public String deserialize(String string) {
        return string;
    }
}