package com.github.MikeKahn.CS356_Assignment1;

import java.util.*;

/**
 * Created by Michael on 10/6/2016.
 * Prefix in class name should represent type of answers(single = 1 choice per student,
 * multiple = allows multiple answers from a student, ect ...)
 *
 */
abstract class Question {

    //Name of the question(title)
    final String name;

    //Question
    final String content;

    int votes;

    //Holds the possible answers with their respective vote count
    private HashMap<String, Integer> choiceCount;

    private ArrayList<String> choices;

    //holds student ids with their respective answers
    HashMap<String, Integer[]> answers;

    //Init question with choiceCount
    Question(String name, String content, String ... choices) {
        this.name = name;
        this.content = content;
        votes = 0;
        answers = new HashMap<>();
        choiceCount = new HashMap<>();
        this.choices = new ArrayList<>(Arrays.asList(choices));
        for(String s: choices) {
            choiceCount.put(s, 0);
        }
    }

    void updateChoice(Integer ... input) {
        for(Integer i: input) {
            choiceCount.put(choices.get(i), choiceCount.get(choices.get(i)) + 1);
        }
    }

    void printResults() {
        for(String choice: choiceCount.keySet()) {
            System.out.println("\t" + choice + ": " + choiceCount.get(choice));
        }
    }

    //Handles the answers given by student, such as whether to only allow 1 answer or multiple.
    abstract void handleAnswers(String student, Integer ... answers);

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder( name + ": " +  content + ", question type: " + this.getClass().getSimpleName());
        for(String key: choiceCount.keySet()) {
            output.append("\n\t").append(key);
        }
        return output.toString();
    }

    int getChoiceCount() {
        return choiceCount.keySet().size();
    }

}
