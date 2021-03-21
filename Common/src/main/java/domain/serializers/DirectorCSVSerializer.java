package domain.serializers;

import domain.Director;
import org.junit.jupiter.params.provider.CsvParsingException;

import java.util.Optional;

public class DirectorCSVSerializer implements ICSVSerializer<Director> {
    @Override
    public String serialize(Director director) {
        return director.toCSV();
    }

    @Override
    public Director deserialize(String string) {
        var fields = string.split(",");

        Optional.of(fields)
                .filter(field -> field.length == 3)
                .orElseThrow(() -> new CsvParsingException("Director CSV string cannot be parsed"));

        var id = Integer.parseInt(fields[0]);
        var name = fields[1];
        var age = Integer.parseInt(fields[2]);

        Director readDirector = new Director(name, age);
        readDirector.setId(id);

        return readDirector;

    }
}