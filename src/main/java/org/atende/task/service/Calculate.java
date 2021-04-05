package org.atende.task.service;

import org.atende.task.data.Data;
import org.atende.task.exceptions.IncorrectDataSymbol;
import org.atende.task.exceptions.IncorrectExpressionException;
import org.atende.task.exceptions.InterpretOperatorException;
import org.atende.task.service.utils.Interpreter;
import org.atende.task.service.utils.RecognizeObject;

import java.util.List;
import java.util.Stack;

public class Calculate {

    private static Calculate INSTANCE;

    private Calculate() {
    }

    public static Calculate getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Calculate();
        }
        return INSTANCE;
    }

    public static void calculateBooleanRPN(String input, Data data)
            throws InterpretOperatorException, IncorrectDataSymbol, IncorrectExpressionException {
        List<String> tokenList = Converter.convertIntoRPN(input, data);
        DisplayBinaryTree.displayRPNBinaryTree(tokenList);
        pressAnyKeyToContinue();

        StringBuilder stringBuilder = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for (String token : tokenList) {
            stringBuilder.append(token);

            if (RecognizeObject.isDigital(stringBuilder.toString())) {
                stack.push(stringBuilder.toString());
            } else if (RecognizeObject.isBooleanOperator(stringBuilder.toString())) {
                boolean result = Interpreter.interpretBooleanOperator(stringBuilder.toString(),
                        Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop()));
                stack.push(String.valueOf(result));
            } else if (RecognizeObject.isLogicOperator(token)) {
                boolean result = Interpreter.interpretLogicOperator(token,
                        Boolean.parseBoolean(stack.pop()), Boolean.parseBoolean(stack.pop()));
                stack.push(String.valueOf(result));
            }
            stringBuilder.setLength(0);
        }

        System.out.println("Result of the calculation is: " + stack.pop());
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("\n\nPress Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}