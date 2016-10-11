package com.github.MikeKahn.CS356_Assignment1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Michael on 10/6/2016.
 */
public class Driver {

    private static long seed = 2134623784123123L;

    private static String[] optionsMain = new String[] {"Configure","Vote","Data","Exit"};
    private static String[] optionsConfigure = new String[] {"List", "Edit", "New", "Remove","Back"};
    private static String[] optionsVote = new String[] {"List","New","Load","Back"};
    private static String[] optionsStudent = new String[] {"List","Open","Back"};
    private static String[] optionsStudentVote = new String[] {"Vote", "View","Back"};
    private static String[] optionsData = new String[] {"List","Open","Back"};

    private static boolean exit = false;

    public static void main(String[] args) {
        System.out.println("Initializing IVote Service.");
        Scanner scanner = new Scanner(System.in);
        IVoteService service = new IVoteService(scanner, seed);

        String input = "";

        while(!input.equals("exit")) {
            printOptions(optionsMain);
            input = scanner.next().toLowerCase();
            if(input.equals("exit")) {
                continue;
            } else if(input.equals("configure")) {
                service.printChildren("configure");
            } else if(input.equals("vote")) {
                service.printChildren("vote");
            } else if(input.equals("data")) {
                service.printChildren("data");
            }
        }
        System.out.println("Program closing.");
    }

    private static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i+1) + ". " + options[i]);
        }
        System.out.print("Please choose an option: ");
    }
}
