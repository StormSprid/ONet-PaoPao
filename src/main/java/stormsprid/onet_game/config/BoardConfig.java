package stormsprid.onet_game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stormsprid.onet_game.model.Board;

@Configuration
public class BoardConfig {
    @Bean
    public Board board(){
        Board board = new Board();
        board.initialize();
        board.fillWithPairs();
        return board;
    }
}
