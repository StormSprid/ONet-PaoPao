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
        Cell a = new Cell(1,1);
        Cell b = new Cell(1,2);
        Cell c = new Cell(1,5);
        board.getCell(b.getX(),b.getY()).setId(-1);
        board.getCell(c.getX(),c.getY()).setId(1);
        board.print();
        Solver solver = new Solver(board);
        solver.initialize(a);

        solver.perform(c);
    }

}
