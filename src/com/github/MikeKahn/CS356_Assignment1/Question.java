package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Michael on 10/6/2016.
 */
public abstract class Question {

    //Name of the question(title)
    public final String name;

    //Holds the possible answers with their respective counts
    public HashMap<String, Integer> choices;

    //holds student ids with their respective answers
    public HashMap<Student, ArrayList<Integer>> answers;

    public Question(String name) {
        this.name = name;
        choices = new HashMap<>();
        answers = new HashMap<>();
    }

    //Handles the answers given by student, such as whether to only allow 1 answer or multiple.
    public abstract void handleAnswers(Student student, ArrayList<Integer> answers);

}
