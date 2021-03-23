package server;

import controller.IActorController;
import exception.InexistentEntity;
import exception.InvalidMessage;
import networking.Message;
import networking.Utils.NetworkUtils;
import utils.ControllerMapper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class HandleTask {
    private HandleTask()
    {
    }

    private static class HeaderParts{
        public String controller;
        public String method;

        public HeaderParts(String controller, String method) {
            this.controller = controller;
            this.method = method;
        }
    }

    public static HeaderParts splitHeader(Message message){
        var headComponents = message.getHeader().split(":");
        if(headComponents.length != 2)
            throw new InvalidMessage("Invalid header");
        return new HeaderParts(headComponents[0], headComponents[1]);
    }

    public static Object[] GetParams(Method method, Message message){

        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        IntStream.range(0, parameterTypes.length).forEach(
                i -> parameters[i] = NetworkUtils.deserializeObject(
                        message.getBody().get(i),
                        parameterTypes[i]
                )
        );

        return parameters;
    }

    public static Message handleTask(Message message) throws Exception
    {
        var headerParts = splitHeader(message);

        if(!ControllerMapper.mapper.containsKey(headerParts.controller))
            throw new InexistentEntity("No such controller");

        var controller = ControllerMapper.mapper.get(headerParts.controller);
        var controllerClass = controller.getClass();


        try{
            var method = Arrays.stream(controllerClass.getDeclaredMethods())
                    .filter(m -> m
                            .getName()
                            .equals(headerParts.method))
                    .findAny()
                    .orElseThrow(NoSuchMethodException::new);

            var parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != message.getBody().size())
            {
                throw new IndexOutOfBoundsException();
            }

            var parameters = GetParams(method, message);

            var returnedValue = method.invoke(controller, parameters);

            if (method.getReturnType().equals(Iterable.class))
            {
                List<String> values = StreamSupport
                        .stream(((Iterable<?>) returnedValue).spliterator(), false)
                        .map(NetworkUtils::serialiseObject)
                        .collect(Collectors.toList());
                return NetworkUtils.successMessage(values);
            }
            return NetworkUtils.successMessage(null);
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return NetworkUtils.failMessage();
    }
}