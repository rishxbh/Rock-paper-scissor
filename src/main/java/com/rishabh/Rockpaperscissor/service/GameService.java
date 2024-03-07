package com.rishabh.Rockpaperscissor.service;

import com.rishabh.Rockpaperscissor.enums.Choice;
import com.rishabh.Rockpaperscissor.model.Game;
import com.rishabh.Rockpaperscissor.exception.GameNotFoundException;
import com.rishabh.Rockpaperscissor.exception.GameOverException;

public interface GameService {

    Game start(String playerOneName, String playerTwoName);

    Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException, GameOverException;

    Game getStatus(Long id) throws GameNotFoundException;
}
