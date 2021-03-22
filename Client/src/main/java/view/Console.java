package view;

import exception.ProgramException;

public class Console {
    public static String handleException(ProgramException e){
        return e.getMessage();
    }
}
