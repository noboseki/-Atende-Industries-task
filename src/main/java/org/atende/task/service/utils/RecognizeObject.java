package org.atende.task.service.utils;

public class RecognizeObject {

    private static RecognizeObject INSTANCE;

    private RecognizeObject() {
    }

    public static RecognizeObject getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecognizeObject();
        }
        return INSTANCE;
    }

    public static boolean isLogicOperator(char character) {
        return character == '&' || character == '|';
    }

    public static boolean isLogicOperator(String logic) {
        return logic.equals("&&") || logic.equals("||");
    }

    public static boolean isBooleanOperator(char character) {
        return character == '<' || character == '>' || character == '=' || character == '!';
    }

    public static boolean isBooleanOperator(String operator) {
        return operator.equals("<") || operator.equals(">") || operator.equals("<=") || operator.equals(">=") || operator.equals("=") || operator.equals("!=");
    }

    public static boolean isDigital(char character) {
        return Character.isDigit(character);
    }

    public static boolean isDigital(String digital) {
        return digital.chars().allMatch(Character::isDigit);
    }

    public static boolean isData(char character) {
        return character == 'a' || character == 'b' || character == 'c' || character == 'd' || character == 'e' || character == 'f' || character == 'g' || character == 'h';
    }

    protected static boolean isBracketOrSpace(char character) {
        return character == '(' || character == ')' || character == ' ';
    }
}
