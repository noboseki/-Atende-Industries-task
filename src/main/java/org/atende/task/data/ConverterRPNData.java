package org.atende.task.data;

public class ConverterRPNData {

    private char character;
    private StringBuilder stringBuilder = new StringBuilder();
    private String inputTextData;

    public ConverterRPNData() {
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public String getInputTextData() {
        return inputTextData;
    }

    public void setInputTextData(String inputTextData) {
        this.inputTextData = inputTextData;
    }
}
