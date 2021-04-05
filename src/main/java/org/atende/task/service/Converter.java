package org.atende.task.service;

import org.atende.task.data.ConverterRPNData;
import org.atende.task.data.Data;
import org.atende.task.exceptions.IncorrectDataSymbol;
import org.atende.task.exceptions.IncorrectExpressionException;
import org.atende.task.service.utils.CheckExpressionCorrectness;
import org.atende.task.service.utils.RecognizeObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Converter {

    private static Converter INSTANCE;

    private Converter() {
    }

    public static Converter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Converter();
        }
        return INSTANCE;
    }

    public static List<String> convertIntoRPN(String input, Data data) throws IncorrectExpressionException, IncorrectDataSymbol {
        input = checkStringAndTransform(input);

        List<String> tokens = new ArrayList<>();


        ConverterRPNData converterRPNData = new ConverterRPNData();
        converterRPNData.setInputTextData(input);

        convertCharacterIteration(data, tokens, converterRPNData);

        CheckExpressionCorrectness.isRPNCorrect(tokens);

        return tokens;
    }

    private static void convertCharacterIteration(Data data, List<String> tokens, ConverterRPNData rpnData) throws IncorrectDataSymbol {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < rpnData.getInputTextData().length(); i++) {
            char character = rpnData.getInputTextData().charAt(i);
            rpnData.setCharacter(character);

            if (RecognizeObject.isData(character)) {
                dataConvert(rpnData, data, tokens);
            }

            else if (RecognizeObject.isDigital(character)) {
                i = digitalConvert(rpnData, tokens, i);
            }

            else if (RecognizeObject.isBooleanOperator(character)) {
                i = booleanOperatorConvert(rpnData, stack, i);
            }

            else if (RecognizeObject.isLogicOperator(character)) {
                i = logicOperatorConvert(rpnData, tokens, stack, i);
            }

            else if (character == '(') {
                rpnData.getStringBuilder().append(character);
                stack.push(rpnData.getStringBuilder().toString());
                rpnData.getStringBuilder().setLength(0);
            }

            else if (character == ')') {
                while (!stack.peek().equals("(")) {
                    tokens.add(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }
    }

    private static int logicOperatorConvert(ConverterRPNData data, List<String> tokens, Stack<String> stack, int i) {
        data.getStringBuilder().append(data.getCharacter());
        data.getStringBuilder().append(data.getInputTextData().charAt(i + 1));

        while (!stack.isEmpty() && RecognizeObject.isBooleanOperator(stack.peek())) {
            tokens.add(stack.pop());
        }

        stack.push(data.getStringBuilder().toString());
        data.getStringBuilder().setLength(0);
        i++;
        return i;
    }

    private static int booleanOperatorConvert(ConverterRPNData data, Stack<String> stack, int i) {
        if (RecognizeObject.isBooleanOperator(data.getInputTextData().charAt(i + 1))) {
            data.getStringBuilder().append(data.getCharacter());
            data.getStringBuilder().append(data.getInputTextData().charAt(i + 1));
            stack.push(data.getStringBuilder().toString());
            data.getStringBuilder().setLength(0);
            i++;
        } else {
            data.getStringBuilder().append(data.getCharacter());
            stack.push(data.getStringBuilder().toString());
            data.getStringBuilder().setLength(0);
        }
        return i;
    }

    private static int digitalConvert(ConverterRPNData data, List<String> tokens, int i) {
        int j = i;
        while (RecognizeObject.isDigital(data.getInputTextData().charAt(j))) {
            data.getStringBuilder().append(data.getInputTextData().charAt(j));
            j++;
            if (j >= data.getInputTextData().length()) {
                break;
            }
        }
        tokens.add(data.getStringBuilder().toString());
        data.getStringBuilder().setLength(0);
        i = j - 1;
        return i;
    }

    private static void dataConvert(ConverterRPNData rpnData, Data data, List<String> tokens) throws IncorrectDataSymbol {
        rpnData.getStringBuilder().append(getDataValue(data, rpnData.getCharacter()));
        tokens.add(rpnData.getStringBuilder().toString());
        rpnData.getStringBuilder().setLength(0);
    }

    private static int getDataValue(Data data, char symbol) throws IncorrectDataSymbol {
        switch (symbol) {
            case 'a': {
                return data.getA();
            }
            case 'b': {
                return data.getB();
            }
            case 'c': {
                return data.getC();
            }
            case 'd': {
                return data.getD();
            }
            case 'e': {
                return data.getE();
            }
            case 'f': {
                return data.getF();
            }
            case 'g': {
                return data.getG();
            }
            case 'h': {
                return data.getH();
            }
            default: {
                throw new IncorrectDataSymbol(String.valueOf(symbol));
            }
        }
    }

    private static String checkStringAndTransform(String input) throws IncorrectExpressionException {
        if (input == null) {
            throw new IncorrectExpressionException("null", "expression cannot be null");
        }
        String cutInput = cutIfEndOfLine(input);
        String transformed = cutInput.replace("and", "&&").replace("or", "||");
        CheckExpressionCorrectness.containsOnlyCorrectSymbols(transformed);
        return transformed;
    }

    private static String cutIfEndOfLine(String input) {
        int indexIf = input.indexOf('\n');
        int indexIfCr= input.indexOf("\r\n");
        if (indexIf != -1){
            return input.substring(0, indexIf -1);
        } else if (indexIfCr != -1) {
            return input.substring(0,indexIfCr);
        }
        return input;
    }

}