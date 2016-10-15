package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;

/**
 * Created by Michael on 10/6/2016.
 */
public class MultipleQuestion extends Question {

    public MultipleQuestion(String name, String content) {
        super(name,content);
    }

    public MultipleQuestion(String name, String content, String ... choices) {
        super(name,content, choices);
    }

    @Override
    public void handleAnswers(Student student, Integer ... input) {
        if(input.length == 0) {
            System.out.println("Input Error: No answer given");
            return;
        }
        updateChoice(input);
        answers.put(student, input);
        votes += input.length;
    }
}
