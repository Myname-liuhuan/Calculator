package com.example.l.calculator;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btdian, btchu, btjian, btadd, btcheng, btclear, dengyu;
    TextView text_input;
    boolean clear_flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        bt0 = (Button) findViewById(R.id.button0);
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        bt5 = (Button) findViewById(R.id.button5);
        bt6 = (Button) findViewById(R.id.button6);
        bt7 = (Button) findViewById(R.id.button7);
        bt8 = (Button) findViewById(R.id.button8);
        bt9 = (Button) findViewById(R.id.button9);
        btadd = (Button) findViewById(R.id.add);
        btjian = (Button) findViewById(R.id.jian);
        btcheng = (Button) findViewById(R.id.cheng);
        btchu = (Button) findViewById(R.id.chu);
        btdian = (Button) findViewById(R.id.dian);
        dengyu = (Button) findViewById(R.id.dengyu);
        btclear = (Button) findViewById(R.id.clear);

        text_input = (TextView) findViewById(R.id.textView1);

        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        btadd.setOnClickListener(this);
        btjian.setOnClickListener(this);
        btcheng.setOnClickListener(this);
        btchu.setOnClickListener(this);
        btdian.setOnClickListener(this);
        dengyu.setOnClickListener(this);
        btclear.setOnClickListener(this);
    }

       public void onClick(View v) {
        String str = text_input.getText().toString();
        switch (v.getId()) {
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.dian:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text_input.setText("");
                }
                text_input.setText(str + ((Button) v).getText());
                break;

            case R.id.add:
            case R.id.jian:
            case R.id.cheng:
            case R.id.chu:
                if (clear_flag) {
                    clear_flag = false;
                    text_input.setText("");
                }
                text_input.setText(str + " " + ((Button) v).getText() + " ");  //在每个运算符 前 后 各加一个 空格
                break;

            case R.id.clear:
                clear_flag = false;
                str = "";
                text_input.setText("");
                break;

            case R.id.dengyu:
                getResult();
                break;
        }
    }

    //运算结果
    private void getResult() {
        String exp = text_input.getText().toString();
        if (exp == null || exp.equals("")) {
            return;
        }
        if (!exp.contains(" ")) {
            return;
        }
        if (clear_flag) {
            clear_flag = false;
            return;
        }
        clear_flag = true;
        String str_1 = exp.substring(0, exp.indexOf(" ")); // 运算符前面的字符
        String str_op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2); //获取到运算符
        String str_2 = exp.substring(exp.indexOf(" ") + 3);   //运算符后面的数字

        double result = 0;
        if (!str_1.equals("") && !str_2.equals("")) {
            double num_1 = Double.parseDouble(str_1);   //先将str_1、str_1强制转化为double类型
            double num_2 = Double.parseDouble(str_2);

            if (str_op.equals("+")) {
                result = num_1 + num_2;
            } else if (str_op.equals("-")) {
                result = num_1 - num_2;
            } else if (str_op.equals("×")) {
                result = num_1 * num_2;
            } else if (str_op.equals("÷")) {
                if (num_2 == 0) {
                    result = 0;
                } else {
                    result = num_1 / num_2;
                }
            }
            if (!str_1.contains(".") && !str_2.contains(".") && !str_op.equals("÷")) {
                int r = (int) result;
                text_input.setText(r + "");
            } else {
                text_input.setText(result + "");
            }
        } else if (!str_1.equals("") && str_2.equals("")) {
            text_input.setText(exp);
        } else if (str_1.equals("") && !str_2.equals("")) {
            double num_2 = Double.parseDouble(str_2);
            if (str_op.equals("+")) {
                result = 0 + num_2;
            } else if (str_op.equals("-")) {
                result = 0 - num_2;
            } else if (str_op.equals("×")) {
                result = 0;
            } else if (str_op.equals("÷")) {
                result = 0;
            }
            if (!str_2.contains(".")) {
                int r = (int) result;
                text_input.setText(r + "");
            } else {
                text_input.setText(result + "");
            }
        } else {
            text_input.setText("");
        }
    }
}