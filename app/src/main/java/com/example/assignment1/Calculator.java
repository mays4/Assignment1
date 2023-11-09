package com.example.assignment1;

import android.util.Log;

import java.util.ArrayList;

public class Calculator {

    private final ArrayList<String> myList;
     private int errorMsg;
     private  int num1 =0;
     private int num2 =0;
//   int flag = 0;
     private  String operand;
     boolean isDividedByZero =false;
    //     String value;
    public Calculator(int errorMsg) {
//        this.value = value;
        this.errorMsg = errorMsg;
        this.myList = new ArrayList<String>();
    }
    public int getPayment_method() {
        return errorMsg;
    }

    public int validate(String value) {
        int flag = 0;
        if (myList.isEmpty()) {
            if (value.equals("+")|| value.equals("-")|| value.equals("*")|| value.equals("/")) {
                // Error: First input is an operator
                flag = 1;
            }
        }
        else {
            String lastValue = myList.get(myList.size() - 1);
            if (isNumeric(lastValue)) {
                if (isNumeric(value)) {
                    // Error: Two numbers in a row
                    flag = 2;
                }
            }
            else {
                if (!isNumeric(value)) {
                    // Error: Two operands in a row
                    flag = 3;
                }
            }
        }
        return flag;
    }


    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void push(String value) {
            myList.add(value);
            Log.d("list", myList.toString());
    }
    public int calculate() {
        int result = 0;
        while (myList.size() > 1) {
            int num1 = Integer.parseInt(myList.remove(0));
            String operator = myList.remove(0);
            int num2 = Integer.parseInt(myList.remove(0));
            Log.d("list of the operation",myList.toString());

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
                    if(num2 == 0){

                        return -1;
                    }
                    result = num1 / num2;
                    break;
            }
            if (myList.size() > 0) {
                myList.add(0, String.valueOf(result));
            }
        }
        return result;
    }



}
