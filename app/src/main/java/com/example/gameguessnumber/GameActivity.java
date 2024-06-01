package com.example.gameguessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    int begin, end, currentNumber; // начало и конец диапазона
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        begin = i.getIntExtra("begin", 0);
        end = i.getIntExtra("end", 100);
        currentNumber = (begin + end) / 2;


        messageTextView = findViewById(R.id.message);
        Button yesButton = findViewById(R.id.yes);
        Button noButton = findViewById(R.id.no);
        Button backButton = findViewById(R.id.back);

        displayMessage("Ваше число больше " + currentNumber + " ?");

        // Обработчики нажатий на кнопки
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия YES
                if (currentNumber < end) {
                    begin = currentNumber + 1;
                    currentNumber = calculateMid(begin, end);
                    displayMessage("Ваше число больше " + currentNumber + " ?");
                } else {
                    displayMessage("Ваше число: " + currentNumber);
                    yesButton.setVisibility(View.INVISIBLE);
                    noButton.setVisibility(View.INVISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                }
            }
        });


        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия NO
                if (currentNumber > begin) {
                    end = currentNumber;
                    currentNumber = calculateMid(begin, end);
                    displayMessage("Ваше число больше " + currentNumber + " ?");
                } else {
                    displayMessage("Ваше число: " + currentNumber);
                    yesButton.setVisibility(View.INVISIBLE);
                    noButton.setVisibility(View.INVISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия кнопки "back"
                navigateToMainActivity();
            }
        });
    }

    // Метод для вычисления середины диапазона
    private int calculateMid(int begin, int end) {
        return (begin + end) / 2;
    }

    // Метод для отображения сообщения в TextView
    private void displayMessage(String message) {
        messageTextView.setText(message);
    }

    // Метод для перехода в MainActivity
    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}