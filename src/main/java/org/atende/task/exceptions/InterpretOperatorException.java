package org.atende.task.exceptions;

public class InterpretOperatorException extends Exception{

    public InterpretOperatorException(String operator) {
        super("Invalid interpret operator: " + operator);
    }
}
