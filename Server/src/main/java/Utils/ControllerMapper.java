package Utils;

import controllers.ActorController;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapper {
    public static Map<String, Object> mapper = new HashMap<>(){{
        put("ActorController", new ActorController());
    }};
}