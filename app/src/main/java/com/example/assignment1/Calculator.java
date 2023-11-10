package com.example.assignment1;

import java.util.ArrayList;

public class Calculator {
    ArrayList<String> myList;

    public Calculator() {

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
                // In case the first input is not a digit
                return -101;
            }
        }
        int result = 0;
        // In case the first input was not a digit
        if (myList.get(0).equals("+") || myList.get(0).equals("-") || myList.get(0).equals("*") || myList.get(0).equals("/")) {
            return -101;
        }
        // In case 'equal' is pressed without finishing the operation
        else if (myList.get(myList.size() - 1).equals("+") || myList.get(myList.size() - 1).equals("-") ||
                myList.get(myList.size() - 1).equals("*") || myList.get(myList.size() - 1).equals("/")) {
            return -104;
        }
        while (myList.size() > 1) {
            //In Case two digit in row
            if (isNumeric(myList.get(0)) && isNumeric(myList.get(1))) {
                return -102;
            }  else if(!isNumeric(myList.get(1)) && !isNumeric(myList.get(2))) {
                return -103;
            }
            else {

                    int num1 = Integer.parseInt(myList.remove(0));
                    String operator = myList.remove(0);
                    if (myList.size() == 0) {
                        return -104;
                    }
                    int num2 = Integer.parseInt(myList.remove(0));

                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                return Integer.MAX_VALUE;
                            }
                            result = num1 / num2;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + operator);
                    }
                    if (!myList.isEmpty()) {
                        myList.add(0, String.valueOf(result));
                    }
            }
        }
        return result;
    }
}