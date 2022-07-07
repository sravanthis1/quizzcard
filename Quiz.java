package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class Quiz extends AppCompatActivity {

    private Helper help = new Helper();
    protected Question[] questions = help.getQuestions(); // Holds the questions

    // Internal handlers
    private int qNum = 0;
    private int score = 0;
    private String[] ans = new String[5]; // user answers

    // View stuff
    TextView questionNumber;
    TextView currentQuestion;
    TextView scoreViewer;
    ProgressBar progressBar;
    Button[] choices = new Button[3];
    Queue<Button> refs = new LinkedList<>(); // for multiple answers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        try {
            this.getSupportActionBar().hide();

        } catch (NullPointerException e) {
        }
        // Set the references
        questionNumber = findViewById(R.id.question_number);
        currentQuestion = findViewById(R.id.current_question);
        scoreViewer = findViewById(R.id.score);
        progressBar = findViewById(R.id.progressBar);
        choices[0] = findViewById(R.id.choice_1);
        choices[1] = findViewById(R.id.choice_2);
        choices[2] = findViewById(R.id.choice_3);
        setUp(qNum);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // I'm making sure this progress bar resets
        progressBar.setProgress(0);
    }


    /**
     * Increases the current question and calls function to perform updates
     *
     * @param v view object
     */
    public void nextQuestion(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Is this your final choice?")
                .setCancelable(false)
                .setPositiveButton("Yes", (d, i) -> {
                    if (questions[qNum].getType() < 2) {
                        score += questions[qNum].calculateAnswer(ans[qNum]);
                    } else {
                        score += questions[qNum].calculateAnswer(ans[qNum].split("//"));
                    }
                    this.qNum++;
                    refs.clear(); // remove all refs for the next question
                    resetBtnColors();
                    setUp(qNum);
                })
                .setNegativeButton("No", (d, i) -> d.cancel());
        AlertDialog confirmation = builder.create();
        confirmation.show();
    }

    /**
     * Performs updates in the view layer according to a given index
     *
     * @param qNum the index of the question at hand
     */
    public void setUp(int qNum) {
        if (qNum > 4) {
            // TODO: show results
            Button exit = findViewById(R.id.confirm);
            String score_text = "Score: " + score;
            exit.setText(R.string.exit);

            questionNumber.setText("");
            scoreViewer.setVisibility(View.GONE);
            progressBar.setProgress(100);
            currentQuestion.setText(score_text);

            for (Button btn : choices) {
                btn.setVisibility(View.GONE);
            }

            exit.setOnClickListener(v -> finish());

        } else {
            int n = qNum + 1;
            int percentage = (qNum * 100 / 5);
            String s = "Question: " + n;
            String score_text = "Score: " + score;

            // Set top info
            questionNumber.setText(s);
            scoreViewer.setText(score_text);
            progressBar.setProgress(percentage);

            // Set the current question
            currentQuestion.setText(questions[qNum].getQuestion());

            // Set the choices
            int i = 0;
            for (String q : questions[qNum].getChoices()) {
                choices[i].setText(q);
                i++;
            }
        }
    }

    public void clickHandler(View view) {
        if (questions[qNum].getType() < 2) {
            // Single answer
            Button ref = findViewById(view.getId());
            // record the answer
            ans[qNum] = ref.getText().toString();
            resetBtnColors();
            ref.setBackgroundResource(R.drawable.white_fill_box);
            ref.setTextColor(getResources().getColor(R.color.retro_blue));
        } else {
            if (refs.size() < 2) {
                refs.add(findViewById(view.getId()));
            } else {
                Button newBut = findViewById(view.getId());
                if (!refs.contains(newBut)) { // check if it hasn't been clicked
                    refs.remove(); // pop the head off
                    refs.add(newBut);
                }
            }
            // Actually set the colors
            ans[qNum] = "";
            resetBtnColors();
            for (Button b : refs) {
                b.setBackgroundResource(R.drawable.white_fill_box);
                b.setTextColor(getResources().getColor(R.color.retro_blue));
                ans[qNum] += b.getText().toString() + "//";
            }

        }

    }

    public void resetBtnColors() {
        for (Button btn : choices) {
            btn.setBackgroundResource(R.drawable.white_outline_box);
            btn.setTextColor(getResources().getColor(R.color.pure_white));
        }
    }
}
