package com.github.MikeKahn.CS356_Assignment1;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michael on 10/6/2016.
 */
public class IVoteService {

    private HashMap<String, Question> questions;
    private HashMap<String, Student> students;

    private HashMap<String, HashMap<String, Method>> commands; // Parent, cmd name, method

    private final Random random;

    private final Scanner scanner;

    public IVoteService(Scanner scanner, long seed) {
        questions = new HashMap<>();
        students = new HashMap<>();
        random = new Random(seed);
        commands = new HashMap<>();
        this.scanner = scanner;
        for (Method m: getClass().getDeclaredMethods()) { //Initialize commands
            if(m.isAnnotationPresent(Command.class)) {
                Command a = m.getAnnotation(Command.class);
                if(commands.containsKey(a.parent())) {
                    commands.get(a.parent()).put(a.name(),m);
                } else { //initialize new children map
                    commands.put(a.parent(), new HashMap<>());
                    commands.get(a.parent()).put(a.name(), m);
                }
            }
        }
    }

    @Command(name="submit", parent="configure")
    public void submitQuestion(String name, Class<? extends Question> cls, String ... choices) {
        try {
            Question question = cls.getConstructor(String.class).newInstance(new Object[] { name });
            question.addChoices(choices);
            questions.put(name,question);
        } catch (Exception e) {
            System.err.println("Invalid question type.");
        }
    }

    @Command(name = "add", parent = "edit")
    public void addChoices(String qName, String ... choices) {
        if(questions.containsKey(qName)) {
            questions.get(qName).addChoices(choices);
        }
    }

    @Command(name = "open", parent = "vote")
    public Question getQuestion(String name) {
        if(questions.containsKey(name)) return questions.get(name);
        throw new NullPointerException(); //Question is not found
    }

    @Command(name = "remove", parent = "configure")
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

    @Command(name = "list", parent = "vote")
    public void printStudentList() {
        for (String str : students.keySet()) {
            System.out.println(str);
        }
    }

    //prints list of all question with # of participants/total votes
    @Command(name = "list", parent = "data")
    public void printQuestionDataBasic() {

    }

    //prints data about specific question
    @Command(name = "get", parent = "data")
    public void printQuestionDataAdv(String questionName) {

    }

    public void action(String name, String parent, Object[] args) {
        if(commands.containsKey(parent) && commands.get(parent).containsKey(name)) {
            try {
                commands.get(parent).get(name).invoke(this, args);
            } catch(Exception e) {
                System.err.println("Command could not execute.");
            }
        } else {
            System.err.println("Invalid command entered.");
        }
    }

    //Prints children of parent command
    public void printChildren(String parent) {
        parent = parent.toLowerCase();
        if(commands.containsKey(parent)) {
            int i = 0;
            for (String cmd : commands.get(parent).keySet()) {
                System.out.println(i + ". " + cmd);
                i++;
            }
            System.out.println(i + ". Back");
            System.out.print("Please choose an option: ");
        } else {
            System.err.println("No parent found by that name.");
        }
    }


}
