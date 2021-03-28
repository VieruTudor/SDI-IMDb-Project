package networking.primitevesSerialization;

import domain.serializers.ICSVSerializer;

public class DoubleSerializer implements ICSVSerializer<Double> {

    @Override
    public String serialize(Double entity) {
        return String.valueOf(entity);
    }

    @Override
    public Double deserialize(String string) {
        return Double.parseDouble(string);
    }
}
