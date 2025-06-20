package stormsprid.onet_game.model;

import lombok.Getter;

import org.springframework.stereotype.Component;
import stormsprid.onet_game.model.BFS.Solver;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

@Component
public class Board {
    int borderId = -1;
    int playableHEIGHT = 10;
    @Getter
    int HEIGHT = playableHEIGHT + 2;
    int playableWIDTH = 20;
    @Getter
    int WIDTH = playableWIDTH + 2;

    @Getter
    char BLOCK = '□';
    List<List<Cell>> cells = new ArrayList<>();

    public Cell getCell(int x, int y) {
        if (y < 0 || y >= cells.size() || x < 0 || x >= cells.get(0).size()) {
            throw new IndexOutOfBoundsException("Wrong cell coords: x=" + x + ", y=" + y);
        }
        return cells.get(y).get(x);
    }
    public void initialize() {
        for (int i = 0; i < HEIGHT; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < WIDTH; j++) {
                Cell cell = new Cell(j,i);
                cell.setId(-1);
                row.add(cell);
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
        for (int y = 1; y < HEIGHT - 1; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {
                Cell c = cells.get(y).get(x);
                fillableCells.add(c);
            }
        }

        if (fillableCells.size() % 2 != 0) {
            throw new IllegalArgumentException("Odd number of cells");
        }

        Collections.shuffle(fillableCells);
        int pairId = 1;
        for (int i = 0; i < fillableCells.size(); i += 2) {
            fillableCells.get(i).setId(pairId);
            fillableCells.get(i + 1).setId(pairId);
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

    public void restartBoard(){
        cells.clear();
        this.initialize();
        this.fillWithPairs();

    }
    public void fillAllWithOnes(){
        for(List<Cell> row : cells){
            for(Cell cell:row){
                if(cell.getId()!=-1) {
                    cell.setId(1);
                }
            }
        }
    }
}



