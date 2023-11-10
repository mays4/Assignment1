package com.example.assignment1;

import java.util.ArrayList;

public class Calculator {

    ArrayList<String> myList;


    public Calculator() {

//        this.errorMsg = errorMsg;
        this.myList = new ArrayList<>();
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void push(String value) {
        myList.add(value);
    }
    public int calculate() {
        if (myList.size() == 1) {
            if (isNumeric(myList.get(0))) {
                return Integer.parseInt(myList.get(0));
            } else {
                //  In case first input not a digit
                return -101;
            }
        }
        int result = 0;
        // In case first input was not digit
        if (myList.get(0).equals("+") || myList.get(0).equals("-") || myList.get(0).equals("*") || myList.get(0).equals("/")) {
            return -101;
            // In case pressed equal without finishing operation
        } else if (myList.get(myList.size() - 1).equals("+") ||myList.get(myList.size() - 1).equals("-")||
                myList.get(myList.size() - 1).equals("*")||myList.get(myList.size() - 1).equals("/")) {
            return -104;
        }

        for (int n = 0; n < myList.size() - 1; n++) {
            String current = myList.get(n);
            if (isNumeric(current)) {
                if (n + 1 < myList.size() && isNumeric(myList.get(n + 1))) {
                    return -102;
                }
                result = Integer.parseInt(current);
                continue;
            }

            String next = myList.get(n + 1);
            // In case two operator added in row
            if (!isNumeric(next)) {
                return -103;
            }

            int nextValue = Integer.parseInt(next);
            switch (current) {
                case "+":
                    result += nextValue;
                    break;
                case "-":
                    result -= nextValue;
                    break;
                case "*":
                    result *= nextValue;
                    break;
                case "/":
                    if (nextValue == 0) {
                        // Error: Divide by zero
                        return Integer.MAX_VALUE;
                    }
                    result /= nextValue;
                    break;
                default:
                    // Error: Invalid operator
                    return -104;
            }
        }
        return result;
    }
}