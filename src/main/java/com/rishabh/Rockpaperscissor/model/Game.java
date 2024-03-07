package com.rishabh.Rockpaperscissor.model;

import com.rishabh.Rockpaperscissor.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Status status;
    private String playerOneName;
    private Integer playerOneScore;
    private String playerTwoName;
    private Integer playerTwoScore;
    @OneToMany
    private List<Round> rounds;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Game(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.rounds = new ArrayList<>();
    }
}
