package com.github.MikeKahn.CS356_Assignment1;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Michael on 10/9/2016.
 */
public class Commands {

    private HashMap<String, Command> commandMap;

    private final Scanner scanner;

    public Commands(Scanner scanner) {
        commandMap = new HashMap<>();
        this.scanner = scanner;
        init();
    }

    private void init() {
        commandMap.put("Configure", new Command("Configure") {
            @Override
            public void action() {
                String input = "";
                while(!input.equals("done")) {
                    System.out.println("Configure Questions:");
                    input = scanner.next();
                }
            }
        });
    }

    private abstract class Command {

        public final String name;

        public Command(String name) {
            this.name = name;
        }

        public abstract void action();

    }

}
