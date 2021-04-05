package org.atende.task.service.utils;

import org.atende.task.exceptions.IncorrectExpressionException;

import java.util.List;

public class CheckExpressionCorrectness {

    private static CheckExpressionCorrectness INSTANCE;

    private CheckExpressionCorrectness() {
    }

    public static CheckExpressionCorrectness getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CheckExpressionCorrectness();
        }
        return INSTANCE;
    }

    public static boolean isRPNCorrect(List<String> tokens) throws IncorrectExpressionException {
        int i = 0;
        for (String token : tokens) {
            if (RecognizeObject.isDigital(token)) {
                i++;
            } else if (RecognizeObject.isBooleanOperator(token) || RecognizeObject.isLogicOperator(token)) {
                i--;
            }
        }
        if (i == 1) {
            return true;
        } else {
            throw new IncorrectExpressionException(tokens.toString(), "cannot calculate RPN");
        }
    }

    public static boolean containsOnlyCorrectSymbols(String calculate) throws IncorrectExpressionException {
        char[] characters = calculate.toCharArray();

        for (Character character : characters) {
            final boolean IncludeCorrectSymbols = RecognizeObject.isLogicOperator(character) || RecognizeObject.isDigital(character)
                    || RecognizeObject.isBooleanOperator(character) || RecognizeObject.isData(character) || RecognizeObject.isBracketOrSpace(character);
            if (!IncludeCorrectSymbols) {
                throw new IncorrectExpressionException(calculate, character.toString());
            }
        }
        return true;
    }
}
