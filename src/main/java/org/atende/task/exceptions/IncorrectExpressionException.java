package org.atende.task.exceptions;

public class IncorrectExpressionException extends Exception{

    public IncorrectExpressionException(String expression, String token) {
        super("Expression : " + expression + " is invalid because :" + token);
    }
}
