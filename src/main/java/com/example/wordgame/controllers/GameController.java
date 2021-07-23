package com.example.wordgame.controllers;

import com.example.wordgame.entitities.Game;
import com.example.wordgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping(value = "/play", consumes = "application/json")
    public Game play(@RequestBody Game game) {
        return service.getCorrectSequenceOfWords(game.getWords());
    }

}
