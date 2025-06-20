package stormsprid.onet_game.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import stormsprid.onet_game.model.BFS.Solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Board {
    int borderId = -1;

    @Getter

    int HEIGHT = 10;
    @Getter

    int WIDTH = 20;
    @Getter
    char BLOCK = '□';
    List<List<Cell>> cells = new ArrayList<>();

    public Cell getCell(int x, int y) {
        return cells.get(y).get(x);
    }

    public void initialize() {
        for (int i = 0; i < HEIGHT; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < WIDTH; j++) {
                row.add(new Cell(i, j));
            }
            cells.add(row);
        }
        this.addBorders();
    }

    private void addBorders() {

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boolean isWidthBorder = (j == 0 || j == WIDTH - 1);
                boolean isHeightBorder = (i == 0 || i == HEIGHT - 1);
                if (isWidthBorder || isHeightBorder) {
                    cells.get(i).get(j).setId(borderId);
                }
            }
        }


    }

    public void print() {

        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                System.out.printf("%2s ", (cell.getId() == -1 ? "□" : cell.getId()));
            }
            System.out.println();

        }


    }

    public void fillWithPairs() {
        List<Cell> fillableCells = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Cell c = cells.get(i).get(j);
                if (c.id != -1) {
                    fillableCells.add(c);
                }
            }
        }

        if (fillableCells.size() % 2 != 0) {
            throw new IllegalArgumentException("Odd number of cells");
        }
        //Collections.shuffle(fillableCells);
        int pairId = 1;
        for (int i = 0; i < fillableCells.size(); i += 2) {
            fillableCells.get(i).id = pairId;
            fillableCells.get(i + 1).id = pairId;
            pairId++;
        }
    }

    public List<List<Cell>> getListOfCells() {
        return cells;
    }


    public boolean canConnect(Cell a, Cell b) {

        if (a.equals(b)) {

            System.out.println("Can connect:" + false);
            return false;
        }
        // Убедимся, что это одинаковые картинки
        if (a.getId() != b.getId()) {

            System.out.println("Can connect:" + false);
            return false;
        };

        Solver solver = new Solver(this);
        boolean result = solver.perform(a, b);
        System.out.println("Can connect:" + result);
        return result;
    }

    public void removePair(Cell a,Cell b){
        a.setId(-1);
        b.setId(-1);
    }
}



