package stormsprid.onet_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stormsprid.onet_game.model.Board;

@SpringBootApplication
public class OnetGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnetGameApplication.class, args);


        Board board = new Board();
        board.initialize();

        board.fillWithPairs();
        board.print();
    }

}
