package com.example.assignment1;
import static com.example.assignment1.R.color.DarkGray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Calculator App
//following requirements are considered
//Allow only single digit operands
//All operators are calculated in the order of entering from left to right (no priority)
//All operators and operands need to be entered in sequence
// i.e operand then operator then operand then operator and so on
//valid result is returned if the user follow the sequence to perform the calculation
//invalid operation is returned if the user don't follow the sequence
//The app has two modes Standard and Advanced
//Advance Mode will show all the history of all the calculations performed by the user
//Standard Mode will just show the current calculation
//User has to press C to clear the display
//App has one activity only and it is designed using Linear Layout
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Calculator calculator;
    Button one_but;
    Button two_but;
    Button three_but;
    Button four_but;
    Button five_but;
    Button six_but;
    Button seven_but;
    Button eight_but;
    Button nine_but;
    Button zero_but;
    Button add_but;
    Button subtract_but;
    Button multiply_but;
    Button divide_but;
    Button equal_but;
    Button clear_but;
    Button advance_but;
    TextView result_text;
    TextView history_text;
    Boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Calculator App", "Activity Created");

        //using calculator class to perform the logic needed in the MainActivity
        calculator = new Calculator();

        //findViewById returns a view by its id
        one_but = findViewById(R.id.onebut);
        two_but = findViewById(R.id.twobut);
        three_but = findViewById(R.id.threebut);
        four_but = findViewById(R.id.fourbut);
        five_but = findViewById(R.id.fivebut);
        six_but = findViewById(R.id.sixbut);
        seven_but = findViewById(R.id.sevenbut);
        eight_but = findViewById(R.id.eightbut);
        nine_but = findViewById(R.id.ninebut);
        zero_but = findViewById(R.id.zerobut);
        add_but = findViewById(R.id.addbut);
        subtract_but = findViewById(R.id.subtractbut);
        multiply_but = findViewById(R.id.multiplybut);
        divide_but = findViewById(R.id.dividebut);
        equal_but = findViewById(R.id.equalbut);
        clear_but = findViewById(R.id.clearbut);
        result_text = findViewById(R.id.result); //display the final result of the calculation
        history_text = findViewById(R.id.historytext); //display the history of all the calculations
        advance_but = findViewById(R.id.advancebut);
        history_text.setMovementMethod(new ScrollingMovementMethod()); //making the history text scrollable

        //setOnClickListener is an event listener that is invoked when a button is pressed
        // it needs View.OnclickListener to perform the logic
        // in its onclick method if the user click the button
        //since the MainActivity is implementing the View.OnclickListener interface
        //so we pass this in the method argument
        one_but.setOnClickListener(this);
        two_but.setOnClickListener(this);
        three_but.setOnClickListener(this);
        four_but.setOnClickListener(this);
        five_but.setOnClickListener(this);
        six_but.setOnClickListener(this);
        seven_but.setOnClickListener(this);
        eight_but.setOnClickListener(this);
        nine_but.setOnClickListener(this);
        zero_but.setOnClickListener(this);
        add_but.setOnClickListener(this);
        subtract_but.setOnClickListener(this);
        multiply_but.setOnClickListener(this);
        divide_but.setOnClickListener(this);
        equal_but.setOnClickListener(this);
        clear_but.setOnClickListener(this);
        advance_but.setOnClickListener(this);
        result_text.setText("");

        //In the Standard Mode the history text will be disabled
        history_text.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Calculator App", "Activity Resumed ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Calculator App", "Activity Paused ");

    }

    @Override
    protected void onDestroy() {
        // save some values
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        //performing click actions on buttons based on their ids
        switch (view.getId()) {

            //if the user click  C button
            case R.id.clearbut:
                //empty both the result String text and input ArrayList
                result_text.setText("");
                calculator.input.clear();
                break;

            //if the user click = button
            case R.id.equalbut:
                //storing the left hand side equation entered by user before equals to in leftEquation
                String leftEquation = result_text.getText().toString();
                //store the result returned by calculate method of calculator class in finalResult
                String finalResult = calculator.calculate();
                //setting the text of the result_text to display full operation
                result_text.setText(leftEquation + " = " + finalResult);
                //if the user is in Advance mode and its flag is true then show the result in history_text
                if (clicked) {
                    String newEquation = result_text.getText().toString();
                    calculator.addHistory(newEquation);
                    String oldEquation = history_text.getText().toString();
                    history_text.setText(oldEquation + "\n" + newEquation);
                }
                break;

            //if the user clicks ADVANCE- WITH HISTORY button
            case R.id.advancebut:
                String advancebut_text = advance_but.getText().toString();
                if (advancebut_text.equals("ADVANCE - WITH HISTORY")) {
                    history_text.setVisibility(View.VISIBLE); //history text is visible
                    advance_but.setText("STANDARD - NO HISTORY"); //change the text
                    clicked = true; //set the flag to true so that operations history can be displayed
                    //change the background color
                    advance_but.setBackgroundColor(ContextCompat.getColor(this, DarkGray));
                    //advance_but.setBackgroundResource(R.color.DimGray); //method deprecated
                    //getcolor returns a color connected with a particular resource ID
                } else {
                    //if the text is "ADVANCE - WITH HISTORY"
                    calculator.operationsHistory.clear();
                    history_text.setText("");
                    history_text.setVisibility(View.GONE); //history text becomes invisible
                    advance_but.setText("ADVANCE - WITH HISTORY");
                    clicked = false;
                    advance_but.setBackgroundColor(ContextCompat.getColor(this, R.color.MediumSlateBlue));
                }
                break;

            default:
                //if the user click any other button
                String newValue = ((Button) view).getText().toString();
                //pushing the newValue in the input ArrayList of calculator class
                calculator.push(newValue);
                String oldValue = result_text.getText().toString();
                //concatenating the previous value with the new value entered by user to display in result_text
                result_text.setText(oldValue + newValue);
        }
    }
}