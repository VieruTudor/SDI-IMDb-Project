package domain.serializers;

public interface ICSVSerializer<T> {
    String serialize(T entity);

    T deserialize(String string) throws Exception;
}
