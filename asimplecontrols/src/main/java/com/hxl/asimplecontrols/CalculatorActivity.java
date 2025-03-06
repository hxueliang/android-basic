package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    // 第一个操作数
    private String firstNum = "";
    // 第二个操作数
    private String secondNum = "";
    // 操作符
    private String operator = "";
    // 要显示的文本内容
    private String showText = "";
    // 当前计算结果
    private String result = "";
    // 文本控件
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_cancel).setOnClickListener(this); // CE
        findViewById(R.id.btn_divide).setOnClickListener(this); // ÷
        findViewById(R.id.btn_multiply).setOnClickListener(this); // ×
        findViewById(R.id.btn_clear).setOnClickListener(this); // C
        findViewById(R.id.btn_seven).setOnClickListener(this); // 7
        findViewById(R.id.btn_eight).setOnClickListener(this); // 8
        findViewById(R.id.btn_nine).setOnClickListener(this); // 9
        findViewById(R.id.btn_plus).setOnClickListener(this); // +
        findViewById(R.id.btn_four).setOnClickListener(this); // 4
        findViewById(R.id.btn_five).setOnClickListener(this); // 5
        findViewById(R.id.btn_six).setOnClickListener(this); // 6
        findViewById(R.id.btn_minus).setOnClickListener(this); // -
        findViewById(R.id.btn_one).setOnClickListener(this); // 1
        findViewById(R.id.btn_two).setOnClickListener(this); // 2
        findViewById(R.id.btn_three).setOnClickListener(this); // 3
        findViewById(R.id.ibtn_sqrt).setOnClickListener(this); // 开方
        findViewById(R.id.btn_reciprocal).setOnClickListener(this); // 1/x
        findViewById(R.id.btn_zero).setOnClickListener(this); // 0
        findViewById(R.id.btn_dot).setOnClickListener(this); // .
        findViewById(R.id.btn_equal).setOnClickListener(this); // =
    }

    @Override
    public void onClick(View v) {

        String inputText = "";

        if (v.getId() == R.id.ibtn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) v).getText().toString();
        }

        int id = v.getId();
        if (id == R.id.btn_clear) {
            // 清除
            clear();
        } else if (id == R.id.btn_cancel) {
            // 取消
        } else if (id == R.id.btn_plus || id == R.id.btn_minus || id == R.id.btn_multiply || id == R.id.btn_divide) {
            // 加减乘除
            operator = inputText;
            refreshText(showText + inputText);
        } else if (id == R.id.btn_equal) {
            // 等于
            Double calculate_result = calculateFour(); // 测试未通过，
            refreshOperate(String.valueOf(calculate_result));
            refreshText(showText + "=" + result);
        } else if (id == R.id.ibtn_sqrt) {
            // 开根号
            double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
            refreshOperate(String.valueOf(sqrt_result));
            refreshText(showText + "√=" + result);
        } else if (id == R.id.btn_reciprocal) {
            // 求倒数
            double sqrt_reciprocal = 1.0 / Double.valueOf(firstNum);
            refreshOperate(String.valueOf(sqrt_reciprocal));
            refreshText(showText + "/=" + result);
        } else {
            // 数字、小数点
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                secondNum = secondNum + inputText;
            }
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }
    }

    // 清空并初始化
    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }

    // 刷新劫数结果
    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = new_result;
        secondNum = "";
        operator = "";
    }

    // 刷新文本显示
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}