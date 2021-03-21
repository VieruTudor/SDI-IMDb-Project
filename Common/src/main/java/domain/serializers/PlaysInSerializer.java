package domain.serializers;

import domain.PlaysIn;
import org.junit.jupiter.params.provider.CsvParsingException;

import java.util.Optional;

public class PlaysInSerializer implements ICSVSerializer<PlaysIn> {
    @Override
    public String serialize(PlaysIn playsIn) {
        return playsIn.toCSV();
    }

    @Override
    public PlaysIn deserialize(String string) {
        var fields = string.split(",");

        Optional.of(fields)
                .filter(field -> field.length == 3)
                .orElseThrow(() -> new CsvParsingException("PlaysIn CSV string cannot be parsed"));

        var movieID = Integer.parseInt(fields[0]);
        var actorID = Integer.parseInt(fields[1]);
        var role = fields[2];

        return new PlaysIn(movieID, actorID, role);
    }
}
