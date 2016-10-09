package com.github.MikeKahn.CS356_Assignment1;

/**
 * Created by Michael on 10/6/2016.
 */
public class Driver {

    private static long seed = 2134623784123123L;

    public static void main(String[] args) {

        IVoteService service = new IVoteService(seed);
        service.submitQuestion("singleTest", SingleQuestion.class, "A");
        service.submitQuestion("multipleTest", MultipleQuestion.class, "B");

        service.addChoices("singleTest", "C", "D", "E");
        service.addChoices("multipleTest", "F", "G", "H");

        System.out.println(service.getQuestion("singleTest"));
        System.out.println(service.getQuestion("multipleTest"));

        /*for (int i = 0; i < 50; i++) {
            service.generateStudent();
        }
        service.printStudentList();*/
    }


}
