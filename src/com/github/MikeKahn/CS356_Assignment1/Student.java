package com.github.MikeKahn.CS356_Assignment1;

import java.util.HashSet;

/**
 * Created by Michael on 10/6/2016.
 */
public class Student {

    //Unique student id
    public final String id;

    //Set of all the questions this student has answered
    public HashSet<String> questions;

    public Student(String id) {
        this.id = id;
        questions = new HashSet<>();
    }
}
