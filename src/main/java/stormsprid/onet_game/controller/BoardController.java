package stormsprid.onet_game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stormsprid.onet_game.model.Board;
import stormsprid.onet_game.model.Cell;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final Board board;
    @Autowired
    public BoardController(Board board) {
        this.board = board;
    }


    @RequestMapping("/board")
    public List<List<Cell>> getBoard(){
        System.out.println("Sending board");

        return  board.getListOfCells();
    }
    @RequestMapping("/kill")
    public boolean killCells(@RequestBody List<Cell> cells){
        if(cells.size()!=2) return false;
        Cell a = board.getCell(cells.get(0).getX(), cells.get(0).getY());
        Cell b = board.getCell(cells.get(1).getX(), cells.get(1).getY());
        if(board.canConnect(a,b)){
            board.removePair(a,b);
            System.out.println("Cells: " + a  + " " + b + "killed");
        }
        return true;
    }
    @PostMapping("/restart")
    public boolean restart(){
        System.out.println("Restarting board");
        board.restartBoard();
        if (board != null || !board.getListOfCells().isEmpty()) {
            return true;
        }
        return false;
        }
    }

