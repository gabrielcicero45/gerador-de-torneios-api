package br.com.xadrez.torneios.controller.dto;

import br.com.xadrez.torneios.model.Performance;
import br.com.xadrez.torneios.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private String name;
    private List<PerformanceDto> performances;

    public PlayerDto(Player player) {
        this.name = player.getName();
        this.performances = PerformanceDto.converter(player.getPerfomances());

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PerformanceDto> getPerformances() {
        return performances;
    }

    public void setPerformances(List<PerformanceDto> performances) {
        this.performances = performances;
    }

    public static List<PlayerDto> converter(List<Player> player){
        return player.stream().map(PlayerDto::new).collect(Collectors.toList());
    }
}
