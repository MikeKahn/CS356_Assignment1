package com.github.MikeKahn.CS356_Assignment1;

/**
 * Created by Michael on 10/6/2016.
 *
 */
class SingleQuestion extends Question {

    SingleQuestion(String name, String content, String ... choices) {
        super(name,content, choices);
    }

    @Override
    public void handleVotes(String id, Integer ... input) {
        if(input.length > 1) { //Too many answers
            System.out.println("Input Error: Question is single choice.");
            return;
        }
        if(input.length == 0) { //No answer given
            System.out.println("Input Error: No answer given");
            return;
        }
        if(answers.containsKey(id)) { //student has previously voted
            removeVotes(answers.get(id));
        }
        //place student and input in answer map, will overwrite old answers as well
        updateVotes(input);
        answers.put(id, input);
        votes++;
    }
}
