package br.com.xadrez.torneios.controller;

import br.com.xadrez.torneios.controller.dto.PlayerDto;
import br.com.xadrez.torneios.controller.form.PlayerForm;
import br.com.xadrez.torneios.model.Game;
import br.com.xadrez.torneios.model.Player;
import br.com.xadrez.torneios.repository.GameRepository;
import br.com.xadrez.torneios.repository.PlayerRepository;
import br.com.xadrez.torneios.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/jogadores")
public class PlayerController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<PlayerDto> list(){
        List<Player> list = playerRepository.findAll();

        return PlayerDto.converter(list);
    }

    @PostMapping
    public void register(@ModelAttribute PlayerForm form){
        Player player = form.convert();
        playerRepository.save(player);
    }

    @PutMapping
    public void registerResult(@RequestParam String name, @RequestParam String points, @RequestParam String game, @RequestParam String white){
        Optional<Player> player = playerRepository.findByName(name);
        Optional<Game> gameFound = gameRepository.findById(Long.valueOf(game));
        if(player.isPresent()){
        if(gameFound.isPresent() && white.equals("true")){
            gameFound.get().updatePerformance(Integer.parseInt(points), true);
        }
        else{
            gameFound.get().updatePerformance(Integer.parseInt(points), false);
        }
            playerRepository.save(player.get());
            gameRepository.save(gameFound.get());
        }
    }
}
