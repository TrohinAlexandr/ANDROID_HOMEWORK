package com.mirea.kt.homework_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mainResult = findViewById(R.id.tvResult);
        TextView fullResult = findViewById(R.id.tvFullResult);
        EditText etPeriod = findViewById(R.id.etPeriod);
        EditText etPercent = findViewById(R.id.etPercent);
        EditText etAmount = findViewById(R.id.etAmount);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(x -> {
            Log.i("calculator_app_tag", "Click submit button");

            fullResult.setText("");
            resultList = new ArrayList<>();

            String periodString = etPeriod.getText().toString();
            String percentString = etPercent.getText().toString();
            String amountString = etAmount.getText().toString();

            if (!periodString.isEmpty() && !percentString.isEmpty() && !amountString.isEmpty()) {
                try {
                    int period = Integer.parseInt(periodString);
                    int percent = Integer.parseInt(percentString);
                    double amount = Double.parseDouble(amountString);

                    if (period <= 0 || percent <= 0 || amount <= 0) {
                        mainResult.setText("Ошибка! Все данные должны быть больше нуля!");
                    } else {
                        int a = 1;
                        double index = (double) percent / 100;
                        while (a <= period) {
                            amount += amount * index;
                            resultList.add("Месяц " + a + ": " + String.format("%.2f", amount));
                            a++;
                        }
                        mainResult.setText("Итоговая сумма: " + String.format("%.2f", amount));

                        for (String str : resultList) {
                            fullResult.append(str + "\n");
                        }
                    }
                } catch (NumberFormatException e) {
                    mainResult.setText("Ошибка! Неверный формат данных!");
                }
            } else {
                mainResult.setText("Ошибка! Все поля должны быть заполнены!");
            }
        });
    }
}