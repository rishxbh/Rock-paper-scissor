package com.rishabh.Rockpaperscissor.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rishabh.Rockpaperscissor.enums.Choice;
import com.rishabh.Rockpaperscissor.enums.Result;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Choice playerOneChoice;
    private Choice playerTwoChoice;
    private Result playerOneResult;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Game game;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Round(
            Choice playerOneChoice,
            Choice playerTwoChoice,
            Result result,
            Game game) {
        this.playerOneChoice = playerOneChoice;
        this.playerTwoChoice = playerTwoChoice;
        this.playerOneResult = result;
        this.game = game;
    }
}
