package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   TextView enteredValue,displayHistory;
   Button plus_btn, minus_btn,multi_btn,divide_btn,one_button, two_btn, three_btn, four_btn, five_btn, six_btn,
           seven_btn, eight_btn,nine_btn,zero_btn,result,clear_btn,history_btn;


    ArrayList<String> listOfHistory;
     Calculator calculatorObj;
//    int errorMsg;
    boolean isHistory = false;
    boolean isNewNum =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredValue= findViewById(R.id.textView);
        plus_btn= findViewById(R.id.plus_btn);
        minus_btn = findViewById(R.id.minus_btn);
        multi_btn= findViewById(R.id.multi_btn);
        divide_btn= findViewById(R.id.divide_btn);
        one_button = findViewById(R.id.one);
        two_btn=findViewById(R.id.two);
        three_btn=findViewById(R.id.three);
        four_btn=findViewById(R.id.four);
        five_btn=findViewById(R.id.five);
        six_btn=findViewById(R.id.six);
        seven_btn=findViewById(R.id.seven);
        eight_btn=findViewById(R.id.eight);
        nine_btn=findViewById(R.id.nine);
        zero_btn=findViewById(R.id.zero);
        result=findViewById(R.id.equal);
        clear_btn= findViewById(R.id.clear);
        history_btn=findViewById(R.id.history);
        displayHistory = findViewById(R.id.textHistory);

        result.setOnClickListener(this);
        one_button.setOnClickListener(this);
        two_btn.setOnClickListener(this);
        three_btn.setOnClickListener(this);
        four_btn.setOnClickListener(this);
        five_btn.setOnClickListener(this);
        six_btn.setOnClickListener(this);
        seven_btn.setOnClickListener(this);
        eight_btn.setOnClickListener(this);
        nine_btn.setOnClickListener(this);
        zero_btn.setOnClickListener(this);
        plus_btn.setOnClickListener(this);
        minus_btn.setOnClickListener(this);
        multi_btn.setOnClickListener(this);
        divide_btn.setOnClickListener(this);
        clear_btn.setOnClickListener(this);
        history_btn.setOnClickListener(this);
        calculatorObj = new Calculator();
       listOfHistory =((myApp)getApplication()).getListOfHistory();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        // clear the display when press clear button
        if (view.getId() == R.id.clear) {
                enteredValue.setText("");
                isNewNum = false;
                calculatorObj = new Calculator();
        } else {

                if (view.getId() == R.id.one || view.getId() == R.id.two
                    || view.getId() == R.id.three || view.getId() == R.id.four ||
                    view.getId() == R.id.five || view.getId() == R.id.six || view.getId() == R.id.seven
                    || view.getId() == R.id.eight || view.getId() == R.id.nine || view.getId() == R.id.zero
                    || view.getId() == R.id.plus_btn || view.getId() == R.id.minus_btn ||
                    view.getId() == R.id.multi_btn || view.getId() == R.id.divide_btn
            ) {
                if (isNewNum) {
                    enteredValue.setText("");
                    isNewNum = false;
                }
                String value = ((Button) view).getText().toString();
                enteredValue.setText(enteredValue.getText() + value);
                calculatorObj.push(value);

            // pressing equal button to get the result
            } else if (view.getId() == R.id.equal) {
                int result = calculatorObj.calculate();
                if (isNewNum) {
                    enteredValue.setText("");
                } else {
                    isNewNum = true;
                    String resultAsString = String.valueOf(result);
                    Log.d("try",resultAsString);
                    // in case divided by zero getting error message
                    if (Integer.parseInt(resultAsString) == Integer.MAX_VALUE) {
                        String msg = "Can not divide by zero";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                        enteredValue.setText("Error");
                    } else if (Integer.parseInt(resultAsString) == -101){
                        String msg = "You should start with a number, not an operator  ";
               Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }
                    else if (Integer.parseInt(resultAsString) == -102){
                        String msg = " you have error not allow to have add two digit in row";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }
                    else if (Integer.parseInt(resultAsString) == -103){
                        String msg = " you have error not allow to have two operator in row";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }else if (Integer.parseInt(resultAsString) == -104){
                        String msg = " Invalid operation";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }
                    else {
                        enteredValue.setText(enteredValue.getText() + "=" + resultAsString);
                        if (isHistory) {
                            String currentDisplayValue = enteredValue.getText().toString();
                            listOfHistory.add(currentDisplayValue);
                            ((myApp) getApplication()).listOfHistory = listOfHistory;
                            ((myApp) getApplication()).index++;
                            StringBuilder stringList = new StringBuilder();
                            for (String s : listOfHistory)
                                stringList.append(s).append("\n");
                            displayHistory.setText(stringList.toString());
                        }
                    }
                }
                // when press in history button
            } else if (view.getId() == R.id.history) {
                isHistory = !isHistory;
                if (isHistory) {
                    history_btn.setText("STANDARD- NO HISTORY");
                    history_btn.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                    StringBuilder stringList = new StringBuilder();
                    for (String s : listOfHistory)
                        stringList.append(s).append("\n");
                    displayHistory.setText(stringList.toString());

                } else {
                    history_btn.setText("ADVANCED - HISTORY");
                    history_btn.setBackgroundColor(getColor(android.R.color.holo_purple));
                    displayHistory.setText("");
                }
                enteredValue.setText("");
                isNewNum = false;
                calculatorObj = new Calculator();
            } else {
                throw new IllegalStateException("Unexpected value: ");
            }
        }
    }

}