package com.github.MikeKahn.CS356_Assignment1;

import java.util.*;

/**
 * Created by Michael on 10/6/2016.
 * Prefix in class name should represent type of answers(single = 1 choice per student,
 * multiple = allows multiple answers from a student, ect ...)
 *
 */
public abstract class Question {

    //Name of the question(title)
    protected final String name;

    //Question
    public final String content;

    protected int votes;

    //Holds the possible answers with their respective vote count
    private HashMap<String, Integer> choiceCount;

    public ArrayList<String> choices;

    //holds student ids with their respective answers
    HashMap<Student, Integer[]> answers;

    //Init question with no choiceCount
    public Question(String name, String content) {
        this.name = name;
        this.content = content;
        choiceCount = new HashMap<>();
        choices = new ArrayList<>();
        answers = new HashMap<>();
        votes = 0;
    }

    //Init question with choiceCount
    public Question(String name, String content, String ... choices) {
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

    protected void updateChoice(Integer ... input) {
        for(Integer i: input) {
            choiceCount.put(choices.get(i), choiceCount.get(choices.get(i)) + 1);
        }
    }

    //add a list of choiceCount(or one) and set initial answer count to zero.
    public void addChoices(String ... choiceList) {
        if(choiceList.length == 0) {
            System.err.println("No choiceCount entered.");
            return;
        }
        for (String c: choiceList) {
            choiceCount.put(c, 0);
            choices.add(c);
        }
    }

    //Resets current student answers(sets all vote counts to zero)
    public void reset() {
        for (String key: choiceCount.keySet()) {
            choiceCount.put(key, 0);
        }
        answers.clear();
    }

    public void printQuestion() {
        System.out.println(content);
        for(String choice: choiceCount.keySet()) {
            System.out.println("\t" + choice);
        }
    }

    public void printResults() {
        for(String choice: choiceCount.keySet()) {
            System.out.println("\t" + choice + ": " + choiceCount.get(choice));
        }
    }

    //Handles the answers given by student, such as whether to only allow 1 answer or multiple.
    public abstract void handleAnswers(Student student, Integer ... answers);

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(name + ", question type: " + this.getClass().getSimpleName());
        for(String key: choiceCount.keySet()) {
            output.append("\n\t" + key);
        }
        return output.toString();
    }

    public int getChoiceCount() {
        return choiceCount.keySet().size();
    }

}
