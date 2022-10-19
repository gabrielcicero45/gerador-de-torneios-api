package br.com.xadrez.torneios.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private List<Performance> perfomances;


    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.perfomances = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Performance> getPerfomances() {
        return perfomances;
    }

    public void setPerfomances(List<Performance> perfomances) {
        this.perfomances = perfomances;
    }
}
