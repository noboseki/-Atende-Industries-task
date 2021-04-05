package org.atende.task.service.utils;

import org.atende.task.exceptions.InterpretOperatorException;

public class Interpreter {

    private static Interpreter INSTANCE;

    private Interpreter() {
    }

    public static Interpreter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Interpreter();
        }
        return INSTANCE;
    }

    public static boolean interpretBooleanOperator(String operator, int objectOne, int objectTwo)
            throws InterpretOperatorException {
        switch (operator) {
            case "=": {
                return objectOne == objectTwo;
            }
            case "!=": {
                return objectOne != objectTwo;
            }
            case ">=": {
                return objectOne >= objectTwo;
            }
            case "<=": {
                return objectOne <= objectTwo;
            }
            case "<": {
                return objectOne < objectTwo;
            }
            case ">": {
                return objectOne > objectTwo;
            }
            default: {
                throw new InterpretOperatorException(operator);
            }
        }
    }

    public static boolean interpretLogicOperator(String operator, boolean objectOne, boolean objectTwo)
            throws InterpretOperatorException {
        switch (operator){
            case "||":{
                return objectOne || objectTwo;
            }
            case "&&":{
                return objectOne && objectTwo;
            }
            default:{
                throw new InterpretOperatorException(operator);
            }
        }
    }
}
