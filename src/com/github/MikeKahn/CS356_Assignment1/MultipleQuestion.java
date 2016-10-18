package com.github.MikeKahn.CS356_Assignment1;

/**
 * Created by Michael on 10/6/2016.
 *
 */
class MultipleQuestion extends Question {

    MultipleQuestion(String name, String content, String ... choices) {
        super(name,content, choices);
    }

    @Override
    public void handleVotes(String id, Integer ... input) {
        if(input.length == 0) {
            System.out.println("Input Error: No answer given");
            return;
        }
        if(answers.containsKey(id)) { //student has previously voted
            removeVotes(answers.get(id));
        }
        updateVotes(input); //update vote counts for new votes
        answers.put(id, input);
        votes += input.length;
    }
}
