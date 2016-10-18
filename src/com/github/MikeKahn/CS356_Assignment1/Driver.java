package com.github.MikeKahn.CS356_Assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Michael on 10/6/2016.
 * Driver for this sumulation
 * Inits the vote service, generates a list of students, generates questions, then has the students
 * randomly vote on questions, abiding by the question types rules(single or multiple answer)
 */
public class Driver {

    public static void main(String[] args) {
        System.out.println("Initializing IVote Service.");
        Random random = new Random();
        IVoteService service = new IVoteService(random);

        System.out.println("Generating student list");
        Set<String> students = new HashSet<>();
        for(int i = 0; i < 50; i++) {
            students.add(service.generateStudent());
        }
        System.out.println("Students generated.");

        System.out.println("Creating questions.");
        ArrayList<Question> questions = getTests();
        for(Question q: questions) { //add all test questions to the service
            service.submitQuestion(q);
        }
        System.out.println("Question generated.");

        System.out.println("Simulating voting.");
        //have students randomly vote for questions
        for(Question q: questions) {
            for(String s: students) {
                if(random.nextInt(101) > 50) {continue;} //decide whether student will vote on given question at 50% chance
                if(q instanceof SingleQuestion) {
                    int answer = random.nextInt(q.getVoteCounts());
                    service.submitAnswers(q.name, s, answer);
                } else if(q instanceof MultipleQuestion) {
                    ArrayList<Integer> answers = new ArrayList<>(q.getVoteCounts()); //create a list for answers with default size set to max amount of answers
                    for(int i = 0; i < q.getVoteCounts(); i++) {
                        if(random.nextInt(101) > 50) {  //50% chance of choosing the answer
                            answers.add(i);
                        }
                    }
                    if(!answers.isEmpty()) { //check to see if there is at least 1 answer from student
                        service.submitAnswers(q.name,s, answers.toArray(new Integer[answers.size()]));
                    }
                }
            }
        }
        //print results
        service.printQuestionDataBasic();
        for (Question q: questions) {
            service.printQuestionDataAdv(q.name);
        }
    }

    //Pre-generated questions
    private static ArrayList<Question> getTests() {
        ArrayList<Question> questions = new ArrayList<>();

        //Question: name, question, answers ...
        questions.add(new SingleQuestion("SingleTest1","What is 4+7?", "-9001", "0", "42", "11"));
        questions.add(new SingleQuestion("SingleTest2","What is the 7th letter of the alphabet?", "A", "Z", "G", "I"));
        questions.add(new MultipleQuestion("MultipleTest1", "Which is your favorite letter?", "A", "B", "C", "D", "E"));
        questions.add(new MultipleQuestion("MultipleTest2", "Which is true", "1. True", "2. False", "3. Not not true", "4. maybe true", "5. probably not false"));
        questions.add(new SingleQuestion("SingleTest3","Is there more than 1 answer to this question?", "Yes", "No"));
        questions.add(new SingleQuestion("SingleTest4","what is the question?", "yes", "no", "maybe", "meh"));
        questions.add(new MultipleQuestion("MultipleTest3", "I ran out of ideas?", "something", "something", "a thing", "not a thing", "life"));
        questions.add(new MultipleQuestion("MultipleTest4", "What is the meaning of stuff?", "more stuff", "nothing", "life", "death", "everything"));

        return questions;
    }
}
