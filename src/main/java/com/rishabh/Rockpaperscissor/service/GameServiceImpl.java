package com.rishabh.Rockpaperscissor.service;

import com.rishabh.Rockpaperscissor.enums.Choice;
import com.rishabh.Rockpaperscissor.enums.Status;
import com.rishabh.Rockpaperscissor.model.Game;
import com.rishabh.Rockpaperscissor.model.Round;
import com.rishabh.Rockpaperscissor.repository.GameRepository;
import com.rishabh.Rockpaperscissor.repository.RoundRepoitory;
import com.rishabh.Rockpaperscissor.enums.Result;
import com.rishabh.Rockpaperscissor.exception.GameNotFoundException;
import com.rishabh.Rockpaperscissor.exception.GameOverException;
import com.rishabh.Rockpaperscissor.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RoundRepoitory roundRepoitory;

    @Override
    public Game start(String playerOneName, String playerTwoName) {
        Game game = createGame(playerOneName, playerTwoName);
        log.info("Started the game");
        return gameRepository.save(game);
    }

    @Override
    public Game play(Long id, Choice playerOneChoice, Choice playerTwoChoice) throws GameNotFoundException, GameOverException {
        Game game = getStatus(id);
        validateGameStatus(game);
        Round round = createRound(playerOneChoice, playerTwoChoice, game);
        addRound(game, round);
        incrementScore(round, game);
        checkGameWinner(game);
        return gameRepository.save(game);
    }

    @Override
    public Game getStatus(Long id) throws GameNotFoundException {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            throw new GameNotFoundException("Game Not Found");
        } else {
            return optionalGame.get();
        }
    }

    private Game createGame(String playerOneName, String playerTwoName) {
        Game game = new Game(playerOneName, playerTwoName);
        game.setStatus(Status.STARTED);
        return game;
    }

    private void validateGameStatus(Game game) throws GameOverException {
        if (!Status.STARTED.equals(game.getStatus())) {
            throw new GameOverException("Game is over, please create new game");
        }
    }

    private Round createRound(Choice playerOneChoice, Choice playerTwoChoice, Game game) {
        Result result = evaluateChoices(playerOneChoice, playerTwoChoice);
        Round round = new Round(playerOneChoice, playerTwoChoice, result, game);
        return roundRepoitory.save(round);
    }

    private Result evaluateChoices(Choice playerOne, Choice playerTwo) {
        validateChoice(playerOne);
        Result result = Result.DRAW;
        if (playerOne.isBetterThan(playerTwo)) {
            result = Result.WIN;
        } else if (playerTwo.isBetterThan(playerOne)) {
            result = Result.LOOSE;
        }
        return result;
    }

    private void validateChoice(Choice playerOneChoice) {
        if (Objects.isNull(playerOneChoice)) {
            String msg = String.format("Choice cannot be empty, playerOneChoice: {}", playerOneChoice);
            throw new IllegalArgumentException(msg);
        }
    }

    private void addRound(Game game, Round round) {
        if (CollectionUtils.isEmpty(game.getRounds())) {
            List<Round> rounds = new ArrayList<>();
            game.setRounds(rounds);
        }
        game.getRounds().add(round);
    }

    private void incrementScore(Round round, Game game) {
        if (round.getPlayerOneResult().equals(Result.WIN)) {
            game.setPlayerOneScore(game.getPlayerOneScore() + 1);
        } else if (round.getPlayerOneResult().equals(Result.LOOSE)) {
            game.setPlayerTwoScore(game.getPlayerTwoScore() + 1);
        }
    }

    private void checkGameWinner(Game game) {
        if (Constants.WINNING_SCORE.equals(game.getPlayerOneScore()) ||
                Constants.WINNING_SCORE.equals(game.getPlayerTwoScore())) {
            game.setStatus(Status.FINISHED);
            log.info("Game Over!!");
        }
    }
}
