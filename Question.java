package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Question {

        private String question;
        private String[] choices;
        private int[] answerIndex; // 1-> single answer, >1 -> multi answers

        public Question(String question,  String[] choices, int answerIndex[]) {
            this.question = question;
            this.answerIndex = answerIndex;
            this.choices = choices;
        }

        // For a multi-answer question
        public int calculateAnswer(String[] choices){
            double score = 0;
            int max_hits = answerIndex.length;
            ArrayList<String> strs = new ArrayList<>();

            // Loop through the right answers
            for(int i : this.answerIndex){
                strs.add(choices[i]);
            }

            // Check for any hits
            for(String ans : choices){
                if(strs.indexOf(ans) >= 0)
                    score += 1;
            }
            return (int)score/max_hits;
        }

        // For a single answer question
        public int calculateAnswer(String choice){
            if(choices[answerIndex[0]].equals(choice))
                return 1;
            return 0;
        }

        /**
         * Returns the question
         * @return
         */
        public String getQuestion() {
            return question;
        }

        /**
         * Returns array of choices for a given question
         * @return
         */
        public String[] getChoices() {
            return choices;
        }

        public int getType(){
            return answerIndex.length;
        }
}
