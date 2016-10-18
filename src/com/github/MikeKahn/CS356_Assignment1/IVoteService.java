package com.github.MikeKahn.CS356_Assignment1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Michael on 10/6/2016.
 * main service for voting, holds all questions and student ids
 */
class IVoteService {

    private HashMap<String, Question> questions; //questions stored by their name
    private HashSet<String> students; //stores all student ids, no need for student to be its own class since it would just contain a String

    private final Random random;

    //use random created in driver for the case in which a seed is used
    IVoteService(Random random) {
        questions = new HashMap<>();
        students = new HashSet<>();
        this.random = random;
    }

    //store question in the service
    void submitQuestion(Question question) {
        questions.put(question.name, question);
    }

    //submit a students answers to a question
    void submitAnswers(String qName, String id, Integer ... answers) {
        if(questions.containsKey(qName)) { //check if question exists
            questions.get(qName).handleAnswers(id, answers);
        }
    }

    //generates a student with a random, unique id. Also stores the student in service before returning the generated id
    String generateStudent() {
        String id = String.valueOf(random.nextInt(Integer.MAX_VALUE)); //Generate initial id, and convert to string
        while(students.contains(id)) { //Check if id is unique, if not generate a new one, cycle till unique
            id =  String.valueOf(random.nextInt(Integer.MAX_VALUE)); //generate new id and convert to string
        }
        students.add(id);
        return id;
    }

    //prints list of all question with # of participants/total votes
    void printQuestionDataBasic() {
        System.out.println("Printing question data(basic).");
        System.out.println("Question: # of votes");
        for (Question question: questions.values()) {
            System.out.println("\t" + question.name + ": " + question.votes);
        }
    }

    //prints data about specific question
    void printQuestionDataAdv(String questionName) {
        if(!questions.containsKey(questionName)) return;
        System.out.println("Printing question data(adv).");
        System.out.println("Question " + questionName + ": " + questions.get(questionName).content);
        System.out.println("Choices: (Choice: # of votes)");
        questions.get(questionName).printResults();
    }
}
