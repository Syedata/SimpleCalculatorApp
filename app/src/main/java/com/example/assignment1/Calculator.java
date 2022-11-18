package com.example.assignment1;

import java.util.ArrayList;

public class Calculator {
    String result;
    int calculationResult;
    String operator;
    int nextNumber;
    ArrayList<String> input = new ArrayList<>(); // String ArrayList to store all user values before =
    ArrayList<String> operationsHistory = new ArrayList<>(); // String ArrayList to store all the operations performed by user

    //method that will add all values of the expression ebtered by user in one list
    public void push(String value) {
        input.add(value);
    }

    //method that will parse the input ArrayList and calculate and return the result of the expression
    public String calculate() {
        //parsing the ArrayList
        for (int i = 0; i < input.size(); i++) {
            //storing the value of the ith element in value variable
            String value = input.get(i);
            //if the index position is divisible by 2, a number is expected otherwise an operator is expected
            if (i % 2 == 0) {  
                if (i == 0) { //in first iteration index 0 will have the first number of the expression
                    //checking if the first value is a number
                    //if the value is any of the operator instead of number
                    // it will break out of the for loop and set the result to invalid operation
                    if (isOperator(value)) {
                        result = "Invalid operation";
                        break;
                    } else {
                        //if the value is number store the value in calculationResult
                        calculationResult = Integer.parseInt(value);
                    }
                }
                // index will no longer be zero for other iterations
                else { ////checking if the value of the ith position is a number
                    // if the value is any of the operator instead of number then
                    // it will break out of for loop and set the result to invalid operation
                    if (isOperator(value)) {
                        result = "Invalid operation";
                        break;
                    }
                    //if the value is a number
                    else {
                        //store the value of ith position in nextNumber variable
                        nextNumber = Integer.parseInt(value);
                        //perform switch on the operator that we stored previously 
                        //for first iteration calculationResult will be the first number of expression
                        //and for next iterations calculationResult will store the result from the previous calculation
                        //and will be updated again after every calculation
                        switch (operator) {
                            case "+":
                                calculationResult = calculationResult + nextNumber;
                                break;
                            case "-":
                                calculationResult = calculationResult - nextNumber;
                                break;
                            case "*":
                                calculationResult = calculationResult * nextNumber;
                                break;
                            case "/":
                                calculationResult = calculationResult / nextNumber;
                                break;
                            default:
                                break;
                        }
                    }
                }
                //converting calculationResult to String and save it in result variable
                result = String.valueOf(calculationResult);
            } else {//if the ith element is not divisible by 2 an operator is expected

                // if the user enters a number instead of operator then
                // it will break out of for loop and set the result to invalid operation
                if (!isOperator(value)) {
                    result = "Invalid operation";
                    break;
                }
                // if the user enters the operator store its value in operator variable
                operator = value;
            }
        }
        return result;
    }

    //method returning if the value is any of the operator
    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

    //method that will add all the calculations performed by the user when the advance mode is selected
    public void addHistory(String operations) {
        operationsHistory.add(operations);
    }
}
