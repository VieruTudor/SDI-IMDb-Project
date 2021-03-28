package networking.primitevesSerialization;

import domain.serializers.ICSVSerializer;

public class IntegerSerializer implements ICSVSerializer<Integer>
{
    @Override
    public String serialize(Integer entity) {
        return String.valueOf(entity);
    }

    @Override
    public Integer deserialize(String string) {
        return Integer.parseInt(string);
    }
}