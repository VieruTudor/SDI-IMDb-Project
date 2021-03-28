package utils;

import java.util.HashMap;
import java.util.Map;

import controllers.*;
import database.serializers.ActorDBTranslator;
import database.serializers.DirectorDBTranslator;
import database.serializers.MovieDBTranslator;
import database.serializers.PlayInDBTranslator;
import repository.DBRepository;

public class ControllerMapper {
    public static Map<String, Object> mapper = new HashMap<>(){{
        put("ActorController", new ActorController(new DBRepository<>(new ActorDBTranslator())));
        put("MovieController", new MovieController(new DBRepository<>(new MovieDBTranslator())));
        put("DirectorController", new DirectorController(new DBRepository<>(new DirectorDBTranslator())));
        put("PlaysInController", new PlaysInController(new DBRepository<>(new PlayInDBTranslator())));
    }};
}