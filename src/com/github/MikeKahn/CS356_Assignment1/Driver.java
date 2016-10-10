package com.github.MikeKahn.CS356_Assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Michael on 10/6/2016.
 */
public class Driver {

    private static long seed = 2134623784123123L;

    private static String optionsMain = "1. Configure\n2. Vote\n3. Data\n4. Exit";

    private static boolean exit = false;

    //TODO: change command system to be more extensible

    public static void main(String[] args) {
        System.out.println("Initializing IVote Service.");
        IVoteService service = new IVoteService(seed);

        Scanner scanner = new Scanner(System.in);

        Commands commands = new Commands(scanner);

        while(!exit) {
            System.out.println(optionsMain);

            System.out.print("Please choose an option:");
            if(scanner.hasNext()) {
                String input = scanner.next().toLowerCase();
            } else {
                System.err.println("Invalid input entered.");
            }
        }
    }


}
