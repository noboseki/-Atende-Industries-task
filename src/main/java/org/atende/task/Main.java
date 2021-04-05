package org.atende.task;

import org.atende.task.data.Data;
import org.atende.task.service.Calculate;

public class Main {

    public static void main(String[] args) throws Exception {
        Data data = new Data(1, 2, 3, 4, 5, 6, 7, 8);
        String input = "(( a = 2 or b = 45 ) or (c = 45))";

        Calculate.calculateBooleanRPN(input, data);
    }

}
