package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ImageView icons;
    TextView showResult;
    StringBuilder input;
    double num1,num2;
    char op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //will convert them into num1,num2 and op
        icons=findViewById(R.id.one);
        icons=findViewById(R.id.two);
        icons=findViewById(R.id.three);
        icons=findViewById(R.id.four);
        icons=findViewById(R.id.five);
        icons=findViewById(R.id.six);
        icons=findViewById(R.id.seven);
        icons=findViewById(R.id.eight);
        icons=findViewById(R.id.nine);
        icons=findViewById(R.id.add);
        icons=findViewById(R.id.minus);
        icons=findViewById(R.id.multiply);
        icons=findViewById(R.id.divide);
        icons=findViewById(R.id.module);
        icons=findViewById(R.id.dot);
        icons=findViewById(R.id.equals);
        icons=findViewById(R.id.c);
        icons=findViewById(R.id.ac);
        showResult=findViewById(R.id.result);
        input = new StringBuilder();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void operation(View view){
        ImageView img=(ImageView) view;
        String tappedImage= img.getTag().toString();
        switch (tappedImage){
            case"+":
            case"-":
            case"*":
            case"/":
            case"%":
                op=tappedImage.charAt(0);
                showResult.setText(tappedImage);
                if(input.length()>0){
                    num1=Double.parseDouble(input.toString());
                    input.setLength(0);

                }
                break;
            case"=":
                if(input.length()>0){
                    num2=Double.parseDouble(input.toString());
                    double result=calculate(num1,num2,op);
                    if(isInteger(result)){
                        showResult.setText(String.valueOf((int)result));
                    }
                    else {
                        showResult.setText(String.valueOf(result));
                    }
                    input.setLength(0);
                }
                break;
            case"c": if(input.length()>0){
                input.deleteCharAt(input.length()-1);
                showResult.setText(input.toString());
            }
            break;
            case"ac":
                input.setLength(0);
                showResult.setText("0");
                break;
            default:
                input.append(tappedImage);
                if(input.length()>8){
                    Toast.makeText(this, "No more numbers can be entered!",Toast.LENGTH_SHORT).show();
                    break;
                }
                showResult.setText(input.toString());

                break;
        }


    }
    public double calculate(double num1,double num2,char op){
        double result;
        switch (op){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num1-num2;
                break;
            case '*':
                result=num1*num2;
                break;
            case '/':
                if (num2 != 0) {
                    result= num1 / num2;
                } else {
                    result=  Double.NaN; // Division by zero
                }
                break;
            case '%':
                result=num1%num2;
                break;
            default:
                result=  Double.NaN;
                break;

        }
        return result;
    }
    public boolean isInteger(double num){
        return Math.floor(num)==num;
    }
}