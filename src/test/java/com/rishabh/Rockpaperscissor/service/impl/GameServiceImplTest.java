package com.rishabh.Rockpaperscissor.service.impl;

import com.rishabh.Rockpaperscissor.repository.GameRepository;
import com.rishabh.Rockpaperscissor.repository.RoundRepoitory;
import com.rishabh.Rockpaperscissor.enums.Choice;
import com.rishabh.Rockpaperscissor.enums.Status;
import com.rishabh.Rockpaperscissor.exception.GameNotFoundException;
import com.rishabh.Rockpaperscissor.exception.GameOverException;
import com.rishabh.Rockpaperscissor.model.Game;
import com.rishabh.Rockpaperscissor.service.GameService;
import com.rishabh.Rockpaperscissor.service.GameServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private RoundRepoitory roundRepoitory;
    @InjectMocks
    private GameService gameService = new GameServiceImpl();


    // status check

    @Test(expected = GameNotFoundException.class)
    public void shouldThrowGameNotFoundExceptionInGetStatus() throws GameNotFoundException {

        // mocking repo response
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        // testing method
        gameService.getStatus(1L);
    }

    // play check

    @Test(expected = GameOverException.class)
    public void shouldThrowGameOverExceptionInPlay() throws GameOverException, GameNotFoundException {

        // mocking repo response
        Game givenGame = new Game();
        givenGame.setStatus(Status.FINISHED);
        Long givenId = 1L;
        when(gameRepository.findById(any())).thenReturn(Optional.ofNullable(givenGame));

        // testing method
        gameService.play(givenId, Choice.getRandom(), Choice.getRandom());

    }

    @Test(expected = GameNotFoundException.class)
    public void shouldThrowGameNotFoundExceptionInPlay() throws GameNotFoundException, GameOverException {

        // mocking repo response
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        // testing method
        gameService.play(1L, Choice.getRandom(), Choice.getRandom());
    }

}
