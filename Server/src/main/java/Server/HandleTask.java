package server;

import Utils.ControllerMapper;
import controller.IActorController;
import exception.InexistentEntity;
import networking.Message;
import networking.Utils.NetworkUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

// WE WILL USE LIKE THIS
// Client -> Message {
//          Header: ControllerName:methodName
//          Body: el1,el2,el3...

public class HandleTask {
    private HandleTask()
    {
    }


    public static Message handleTask(Message message,
                                     IActorController actorController) throws Exception
    {
        // SPLIT THIS FUNCTION INTO SMALLER ONE
        // SINGLE RESPONSIBILITY PRINCIPLE ( ASK GABI IF YOU DON'T KNOW )
        var headerComponents = message.getHeader().split(":");
        if(headerComponents.length != 2)
            throw new Exception("Invalid Header"); // MAKE GOOD EXCEPTION

        var controllerName = message.getHeader().split(":")[0];
        System.out.println(controllerName);
        if(!ControllerMapper.mapper.containsKey(controllerName))
            throw new InexistentEntity("No such controller");

        var controller = ControllerMapper.mapper.get(controllerName);

        var methodName = message.getHeader().split(":")[1];

        // CHECK IF METHOD EXIST IN CONTROLLER

        var controllerClass = controller.getClass();
        try{
            Method method = Arrays.stream(controllerClass.getDeclaredMethods())
                    .filter(m -> m
                            .getName()
                            .equals(methodName))
                    .findAny()
                    .orElseThrow(NoSuchMethodException::new);

            Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != message.getBody().size())
            {
                throw new IndexOutOfBoundsException();
            }
//          This should work , test later
//            ArrayList<Object> parameters = new ArrayList<>();
//            IntStream.range(0, parameterTypes.length).forEach(
//                    i -> {
//                        parameters.set(i, NetworkUtils.deserializeObject(
//                                message.getBody().get(i),
//                                parameterTypes[i]
//                        ));
//                    }
//            );
            Object[] parameters = new Object[parameterTypes.length];
            IntStream.range(0, parameterTypes.length).forEach(
                    i -> parameters[i] = NetworkUtils.deserializeObject(
                            message.getBody().get(i),
                            parameterTypes[i]
                    )
            );

            Object returnedValue = method.invoke(controller, parameters);

            if (method.getReturnType().equals(Iterable.class))
            {
                List<String> values = StreamSupport
                        .stream(((Iterable<?>) returnedValue).spliterator(), false)
                        .map(NetworkUtils::serialiseObject)
                        .collect(Collectors.toList());
                return NetworkUtils.success(values);
            }
            return NetworkUtils.success(null);
        }
        catch(Exception e){
            System.out.println("Am belit pula grav");
        }


        return null;
    }
}