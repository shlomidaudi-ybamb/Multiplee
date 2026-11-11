package com.example.multiplee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MathViewModel extends ViewModel {

    private Model model;

    private MutableLiveData<Integer> num1 =
            new MutableLiveData<>();

    private MutableLiveData<Integer> num2 =
            new MutableLiveData<>();

    private MutableLiveData<Boolean> answerCorrect =
            new MutableLiveData<>();

    private MutableLiveData<Integer> correctCount =
            new MutableLiveData<>(0);

    private MutableLiveData<Integer> wrongCount =
            new MutableLiveData<>(0);

    public MathViewModel() {
        model = new KefelModel();
        generateQuestion();
    }

    // חשיפת הנתונים למצג

    public LiveData<Integer> getNum1() {
        return num1;
    }

    public LiveData<Integer> getNum2() {
        return num2;
    }

    public LiveData<Boolean> getAnswerCorrect() {
        return answerCorrect;
    }

    public LiveData<Integer> getCorrectCount() {
        return correctCount;
    }

    public LiveData<Integer> getWrongCount() {
        return wrongCount;
        // bla bla bla
    }

    // החלפת סוג המשחק

    public void setGameType(GameType gameType) {

        switch (gameType) {

            case KEFEL:
                model = new KefelModel();
                break;

            case UPTO20:
                model = new Upto20Model();
                break;

            case CHALLENGE:
                model = new ChallengeModel();
                break;
        }

        generateQuestion();
    }

    // הגרלת תרגיל חדש

    public void generateQuestion() {

        model.generateNumbers();

        num1.setValue(model.getNum1());
        num2.setValue(model.getNum2());

        answerCorrect.setValue(null);
    }

    // בדיקת תשובת המשתמש

    public void checkAnswer(int answer) {

        boolean correct = model.checkAnswer(answer);

        answerCorrect.setValue(correct);

        if (correct) {
            correctCount.setValue(
                    correctCount.getValue() + 1
            );
            generateQuestion();
        } else {
            wrongCount.setValue(
                    wrongCount.getValue() + 1
            );
        }
    }
}