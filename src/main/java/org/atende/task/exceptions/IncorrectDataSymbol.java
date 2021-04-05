package org.atende.task.exceptions;

public class IncorrectDataSymbol extends Exception{

    public IncorrectDataSymbol(String symbol) {
        super("Incorrect data symbol: " + symbol);
    }
}
