package com.example.multiplee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private MathViewModel viewModel;

    private TextView num1Text;
    private TextView num2Text;

    private EditText answerBox;

    private Button kefelButton;
    private Button upto20Button;
    private Button challengeButton;
    private Button checkButton;

//    private TextView correctCountText;
//    private TextView wrongCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // קישור לרכיבי ה-Layout

        num1Text = findViewById(R.id.tvNum1);
        num2Text = findViewById(R.id.tvNum2);

        answerBox = findViewById(R.id.answerBox);

        kefelButton = findViewById(R.id.btnKefel);
        upto20Button = findViewById(R.id.btnUpto20);
        challengeButton = findViewById(R.id.btnChallenge);
        checkButton = findViewById(R.id.btnCheck);

//        correctCountText =
//                findViewById(R.id.correctCountText);
//
//        wrongCountText =
//                findViewById(R.id.wrongCountText);

        // יצירת ViewModel

        viewModel = new ViewModelProvider(this)
                .get(MathViewModel.class);

        // הרשמה לשינויים במספר הראשון

        viewModel.getNum1().observe(this, number -> {
            num1Text.setText(String.valueOf(number));
        });

//        viewModel.getNum1().observe(this, num1 -> num1Text.setText(String.valueOf(num1)));

        // הרשמה לשינויים במספר השני

        viewModel.getNum2().observe(this, number -> {
            num2Text.setText(String.valueOf(number));
        });

        // הרשמה לתוצאות הבדיקה

        viewModel.getAnswerCorrect().observe(this, correct -> {

            if (correct == null) { // new question
                answerBox.setText("");
                return;

            }

            if (correct) {
                Toast.makeText(this,
                        "Correct!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Wrong answer!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // הרשמה למוני התשובות

//        viewModel.getCorrectCount().observe(this, count -> {
//            correctCountText.setText(String.valueOf(count));
//        });
//
//        viewModel.getWrongCount().observe(this, count -> {
//            wrongCountText.setText(String.valueOf(count));
//        });

        // הגדרת מאזינים לכפתורים

        kefelButton.setOnClickListener(this);
        upto20Button.setOnClickListener(this);
        challengeButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btnKefel) {

            viewModel.setGameType(GameType.KEFEL);
            answerBox.setText("");

        } else if (id == R.id.btnUpto20) {

            viewModel.setGameType(GameType.UPTO20);
            answerBox.setText("");

        } else if (id == R.id.btnChallenge) {

            viewModel.setGameType(GameType.CHALLENGE);
            answerBox.setText("");

        } else if (id == R.id.btnCheck) {

            String answer = answerBox.getText().toString();

            if (answer.isEmpty()) {
                Toast.makeText(this,
                        "Please enter an answer",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int numAnswer = Integer.parseInt(answer);

            viewModel.checkAnswer(numAnswer);
        }
    }
}