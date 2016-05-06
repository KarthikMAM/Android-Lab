package com.example.karthikmam.ex03;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    EditText etInput;

    Hashtable<Character, Integer> prec = new Hashtable<Character, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.etInput);

        prec.put('*', 2);
        prec.put('/', 2);
        prec.put('+', 1);
        prec.put('-', 1);
        prec.put('$', 0);
    }

    public void btClick(View view) {
        switch(view.getId()) {
            case R.id.btEqual:
                etInput.setText(getResult((etInput.getText().toString() + "$").split(" ")));
                break;
            case R.id.btClear:
                etInput.setText("");
                break;
            default:
                etInput.setText(etInput.getText().toString() + ((Button) view).getText() + " ");
                break;
        }
    }

    private String getResult(String[] params) {
        Stack<Character> operator = new Stack<Character>();
        Stack<Integer> operand = new Stack<Integer>();

        for(String i: params) {
            if(i.charAt(0) >= '0' && i.charAt(0) <= '9') {
                operand.push(Integer.parseInt(i));
            } else {
                if (!operator.isEmpty()) {
                    Log.d("Current", (Integer)prec.get(i.charAt(0)) + "");
                    Log.d("Peek", (Integer)prec.get(operator.peek()) + "");
                }
                while(!operator.isEmpty() && (Integer)prec.get(i.charAt(0)) <= (Integer) prec.get(operator.peek())) {
                    int op2 = operand.pop();
                    int op1 = operand.pop();

                    switch (operator.pop()) {
                        case '+':
                            operand.push(op1 + op2);
                            break;
                        case '-':
                            operand.push(op1 - op2);
                            break;
                        case '*':
                            operand.push(op1 * op2);
                            break;
                        case '/':
                            operand.push(op1 / op2);
                            break;
                    }
                }
                operator.push(i.charAt(0));
            }
        }

        return operand.pop().toString();
    }
}
