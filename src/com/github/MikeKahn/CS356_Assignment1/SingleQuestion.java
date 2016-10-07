package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;

/**
 * Created by Michael on 10/6/2016.
 */
public class SingleQuestion extends Question {

    public SingleQuestion(String name) {
        super(name);
    }


    //TODO: Check if no answer is allowed
    @Override
    public void handleAnswers(Student student, ArrayList<Integer> input) {
        if(input.size() > 1) { //Too many answers
            System.out.println("Input Error: Question is single choice.");
            return;
        }
        if(input.size() == 0) { //No answer given
            System.out.println("Input Error: No answer given");
            return;
        }
        //place student and input in answer map, will overwrite old answers as well
        answers.put(student, input);
    }
}
