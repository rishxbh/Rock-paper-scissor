package com.rishabh.Rockpaperscissor.controller;

import com.rishabh.Rockpaperscissor.enums.Choice;
import com.rishabh.Rockpaperscissor.exception.GameNotFoundException;
import com.rishabh.Rockpaperscissor.exception.GameOverException;
import com.rishabh.Rockpaperscissor.model.Game;
import com.rishabh.Rockpaperscissor.service.GameService;
import com.rishabh.Rockpaperscissor.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game startGame(@RequestParam("playerOneName") String playerOneName) {
        return gameService.start(playerOneName, Constants.PLAYER_TWO_NAME);
    }

    @PutMapping("/play/{gameId}")
    public Game playGame(
            @PathVariable("gameId") Long id,
            @RequestParam("choice") Choice playerOneChoice) throws GameOverException, GameNotFoundException {
        Choice playerTwoChoice = Choice.getRandom();
        return gameService.play(id, playerOneChoice, playerTwoChoice);
    }
    @GetMapping("/status/{gameId}")
    public Game getGameStatus(
            @PathVariable("gameId") Long id) throws GameNotFoundException {
        return gameService.getStatus(id);
    }

}
