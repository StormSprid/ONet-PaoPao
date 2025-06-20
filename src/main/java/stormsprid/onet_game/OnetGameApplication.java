package stormsprid.onet_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stormsprid.onet_game.model.BFS.Solver;
import stormsprid.onet_game.model.Board;
import stormsprid.onet_game.model.Cell;

@SpringBootApplication
public class OnetGameApplication {

    public static void main(String[] args) {
        //SpringApplication.run(OnetGameApplication.class, args);


        Board board = new Board();
        board.initialize();

        board.fillWithPairs();
        board.print();
        Cell a = board.getCell(17, 1);

        Cell b = board.getCell(18, 1);
        System.out.println(b.getId() + " " + a.getId());
        board.canConnect(a, b);

    }
}
