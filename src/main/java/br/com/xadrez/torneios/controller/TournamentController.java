package br.com.xadrez.torneios.controller;

import br.com.xadrez.torneios.controller.dto.TournamentDto;
import br.com.xadrez.torneios.controller.form.TournamentForm;
import br.com.xadrez.torneios.model.Tournament;
import br.com.xadrez.torneios.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/torneios")
public class TournamentController {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    /*@GetMapping
    public List<TournamentDto> list(){
        List<Tournament> list = tournamentRepository.findAll();
        return TournamentDto.converterList(list);
    }*/

    @GetMapping
    public TournamentDto find(@RequestParam String name){
        Optional<Tournament> tournament = tournamentRepository.findByName(name);
        return TournamentDto.converter(tournament);
    }

    @PostMapping
    public void register(@ModelAttribute TournamentForm form) throws Exception {
        Tournament tournament = form.convert(playerRepository,gameRepository,roundRepository,performanceRepository);
        tournamentRepository.save(tournament);
    }

}
