package com.example.quiz;

public class Helper {
    private Question[] questions = new Question[5];


    public Helper() {
        questions[0] = new Question("How many planets are there in universe?",
                new String[]{"6", "8", "10"},
                new int[]{1}
        );
        questions[1] = new Question("Who is first person to land on moon?",
                new String[]{"Neil Armstrong", "Thomas Edison", "Carlson"},
                new int[]{0}
        );
        questions[2] = new Question("What are the programming languages used in android studio? (multiple answers)",
                new String[]{"Java", "Kotlin",
                        "React"},
                new int[]{0, 1}
        );
        questions[3] = new Question("Which is the closest state to New Jersey?",
                new String[]{"NewYork",
                        "Texas", "California"},
                new int[]{0}
        );
        questions[4] = new Question("Which is the fastest car in the world?",
                new String[]{"Jeep", "Bugati", "Ferrari"},
                new int[]{2}
        );

    }

    public Question[] getQuestions() {
        return questions;
    }
}

