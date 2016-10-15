package com.github.MikeKahn.CS356_Assignment1;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Michael on 10/6/2016.
 */
public class IVoteService {

    private HashMap<String, Question> questions;
    private HashMap<String, Student> students;

    private final Random random;

    public IVoteService(Random random) {
        questions = new HashMap<>();
        students = new HashMap<>();
        this.random = random;
    }

    public void submitQuestion(String name, String content, Class<Question> type) {
        try {
            questions.put(name, type.getConstructor(String.class, String.class).newInstance(name, content));
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void submitQuestion(Question question) {
        questions.put(question.name, question);
    }

    public void addChoices(String qName, String ... choices) {
        if(questions.containsKey(qName)) {
            questions.get(qName).addChoices(choices);
        }
    }

    public Question getQuestion(String name) {
        if(questions.containsKey(name)) return questions.get(name);
        throw new NullPointerException(); //Question is not found
    }

    public void submitAnswers(String qName, String student, Integer ... answers) {
        if(questions.containsKey(qName)) {
            questions.get(qName).handleAnswers(getStudent(student), answers);
            getStudent(student).addQuestion(qName);
        }
    }

    public void removeQuestion(String name) {
        if(questions.containsKey(name)) questions.remove(name);
    }

    public String generateStudent() {
        String id = String.valueOf(random.nextInt(Integer.MAX_VALUE)); //Generate initial id
        while(students.containsKey(id)) { //Check if id is unique, if not generate a new one, cycle till unique, might change this is to create ids sequentially(possibly faster)
            id =  String.valueOf(random.nextInt(Integer.MAX_VALUE));
        }
        Student student = new Student(id);
        students.put(id, student);
        return id;
    }

    public void printStudentList() {
        for (String str : students.keySet()) {
            System.out.println(str);
        }
    }

    public Student getStudent(String id) {
        return (students.containsKey(id)) ? students.get(id) : null;
    }

    //prints list of all question with # of participants/total votes
    public void printQuestionDataBasic() {
        System.out.println("Printing question data(basic).");
        System.out.println("Question: # of votes");
        for (Question question: questions.values()) {
            System.out.println("\t" + question.name + ": " + question.votes);
        }
    }

    //prints data about specific question
    public void printQuestionDataAdv(String questionName) {
        if(!questions.containsKey(questionName)) return;
        System.out.println("Printing question data(adv).");
        System.out.println("Question: " + questionName);
        System.out.println("Choices: (Choice: # of votes)");
        questions.get(questionName).printResults();
    }
}
