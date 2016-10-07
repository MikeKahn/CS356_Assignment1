package com.github.MikeKahn.CS356_Assignment1;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Michael on 10/6/2016.
 */
public class IVoteService {

    private HashMap<String, Question> questions;
    private HashMap<String, Student> students;

    private final Random random;

    public IVoteService(long seed) {
        questions = new HashMap<>();
        students = new HashMap<>();
        random = new Random(seed);
    }

    public void submitQuestion(String name, String ... choices) {

    }

    public Question getQuestion(String name) {
        if(questions.containsKey(name)) return questions.get(name);
        throw new NullPointerException(); //Question is not found
    }

    public void removeQuestion(String name) {
        if(questions.containsKey(name)) questions.remove(name);
    }

    public Student generateStudent() {
        String id = String.valueOf(random.nextInt(Integer.MAX_VALUE)); //Generate initial id
        while(students.containsKey(id)) { //Check if id is unique, if not generate a new one, cycle till unique, might change this is to create ids sequentially(possibly faster)
            id =  String.valueOf(random.nextInt(Integer.MAX_VALUE));
        }
        Student student = new Student(id);
        students.put(id, student);
        return student;
    }

    public void printStudentList() {
        for (String str : students.keySet()) {
            System.out.println(str);
        }
    }
}
