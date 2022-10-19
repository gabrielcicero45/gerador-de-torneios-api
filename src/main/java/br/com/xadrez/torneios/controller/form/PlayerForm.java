package br.com.xadrez.torneios.controller.form;

import br.com.xadrez.torneios.model.Player;

public class PlayerForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player convert() {
        return new Player(name);
    }

}
