package domain.serializers.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBTranslator<T> {
    String serializer(T entity);

    T deserializer(ResultSet rs) throws SQLException;

    String getTable();

    String getFields();

    String searchById();
}
