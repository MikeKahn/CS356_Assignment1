package com.github.MikeKahn.CS356_Assignment1;

/**
 * Created by Michael on 10/6/2016.
 */
public class Driver {

    private static long seed = 2134623784123123L;

    public static void main(String[] args) {

        IVoteService service = new IVoteService(seed);
        for (int i = 0; i < 50; i++) {
            service.generateStudent();
        }
        service.printStudentList();
    }


}
