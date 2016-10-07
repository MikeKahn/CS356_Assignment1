package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;

/**
 * Created by Michael on 10/6/2016.
 */
public class MultipleQuestion extends Question {

    public MultipleQuestion(String name) {
        super(name);
    }

    //TODO: Decide whether a students already submitted answers are simply overwritten or the new answers added to the old
    @Override
    public void handleAnswers(Student student, ArrayList<Integer> input) {
        if(answers.size() == 0) {
            System.out.println("Input Error: No answer given");
            return;
        }
        answers.put(student, input);
    }
}
