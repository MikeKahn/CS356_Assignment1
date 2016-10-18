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

    //# of votes this question has in total
    int votes;

    //holds student ids with their respective answers
    HashMap<String, Integer[]> answers;

    //Holds the possible answers with their respective vote count
    private HashMap<String, Integer> voteCounts;

    //List of all possible answers to the question
    private ArrayList<String> choices;

    Question(String name, String content, String ... choices) {
        this.name = name;
        this.content = content;
        votes = 0;
        answers = new HashMap<>();
        voteCounts = new HashMap<>();
        this.choices = new ArrayList<>(choices.length); //create new arraylist with init size = to choices.length
        for(String s: choices) {
            if(!voteCounts.containsKey(s)) { //duplicate answer
                voteCounts.put(s, 0);
                this.choices.add(s);
            }
        }
    }

    //changes the vote count for a set of choices
    void updateVotes(Integer ... input) {
        for(Integer i: input) {
            String choice = choices.get(i);
            voteCounts.put(choice, voteCounts.get(choice) + 1); //add 1 to vote count for choice
        }
    }

    //for when a student would change their answers, remove old votes
    void removeVotes(Integer ... input) {
        for(Integer i: input) {
            String choice = choices.get(i);
            voteCounts.put(choice, voteCounts.get(choice) - 1); //sub 1 from vote count for choice
        }
    }

    void printResults() {
        for(String choice: voteCounts.keySet()) {
            System.out.println("\t" + choice + ": " + voteCounts.get(choice));
        }
    }

    //Handles the answers given by student, such as whether to only allow 1 answer or multiple.
    abstract void handleVotes(String student, Integer ... answers);

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder( name + ": " +  content + ", question type: " + this.getClass().getSimpleName());
        for(String key: voteCounts.keySet()) {
            output.append("\n\t").append(key);
        }
        return output.toString();
    }

    int getVoteCounts() {
        return voteCounts.keySet().size();
    }

}
