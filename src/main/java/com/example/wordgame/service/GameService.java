package com.example.wordgame.service;

import com.example.wordgame.entitities.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public Game getCorrectSequenceOfWords(List<String> incomingWords) {
        Game game = new Game();
        List<String> correctSequenceOfWords = new ArrayList<>();
        if(incomingWords.size() != 0 && incomingWords.get(0).length() != 0) {
            correctSequenceOfWords.add(incomingWords.get(0));
            for (int i = 1; i < incomingWords.size(); i++) {
                if(incomingWords.get(i).length() == 0) {
                    game.setWords(correctSequenceOfWords);
                    return game;
                }
                char firstCharOfCurrentWord = incomingWords.get(i).charAt(0);
                char lastCharOfPreviousWord = incomingWords.get(i - 1).charAt(incomingWords.get(i - 1).length() - 1);
                if (!(firstCharOfCurrentWord == lastCharOfPreviousWord) || incomingWords.get(i).length() == 0) {
                    game.setWords(correctSequenceOfWords);
                    return game;
                }
                else correctSequenceOfWords.add(incomingWords.get(i));;
            }
        }
        game.setWords(correctSequenceOfWords);
        return game;
    }
}
