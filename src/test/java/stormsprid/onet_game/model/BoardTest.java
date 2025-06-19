package stormsprid.onet_game.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    Board board = new Board();
    @Test
    public void testPrint(){
        Board board = new Board();
        board.initialize();
        board.fillWithPairs();
        board.print();

    }
    @Test
    public void testCanConnect(){
        Cell a = new Cell(1,1);
        Cell b = new Cell(1,10);
        for (int i = 0;i<10000;i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10001);
            a.setId(id);
            b.setId(id);
            assertTrue(board.canConnect(a,b));
        }


    }
}
