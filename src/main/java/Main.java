import UI.UI;
import controller.Controller;
import domain.serializers.db.ActorDBTranslator;
import domain.serializers.db.DirectorDBTranslator;
import domain.serializers.db.MovieDBTranslator;
import domain.serializers.db.PlayInDBTranslator;
import repository.DBRepository;

import javax.xml.parsers.ParserConfigurationException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException {
        UI console = new UI(
                new Controller(
                        new DBRepository<>( new ActorDBTranslator()),
                        new DBRepository<>( new MovieDBTranslator()),
                        new DBRepository<>(new PlayInDBTranslator()),
                        new DBRepository<>( new DirectorDBTranslator())

                )
        );
        try {
            console.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
