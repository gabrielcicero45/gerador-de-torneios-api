package br.com.xadrez.torneios.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private List<Player> players;
    @ManyToMany
    private List<Round> rounds;

    public Tournament() {
    }

    public Tournament(String name, List<Player> players, List<Round> rounds) {
        this.name = name;
        this.players = players;
        this.rounds = rounds;
    }


    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

}
