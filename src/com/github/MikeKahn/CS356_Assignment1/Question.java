package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 10/6/2016.
 * Prefix in class name should represent type of answers(single = 1 choice per student,
 * multiple = allows multiple answers from a student, ect ...)
 *
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

    //add a list of choices(or one) and set initial answer count to zero.
    public void addChoices(String ... choiceList) {
        if(choiceList.length == 0) {
            System.err.println("No choices entered.");
            return;
        }
        for (String c: choiceList)
            choices.put(c,0);
    }

    //Resets current student answers(sets all vote counts to zero)
    public void reset() {
        for (String key: choices.keySet()) {
            choices.put(key, 0);
        }
        answers.clear();
    }

    //Handles the answers given by student, such as whether to only allow 1 answer or multiple.
    public abstract void handleAnswers(Student student, ArrayList<Integer> answers);

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + ", question type: " + this.getClass().getSimpleName());
        for(String key: choices.keySet()) {
            output.append("\n\t" + key);
        }
        return output.toString();
    }

}
