package stormsprid.onet_game.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Board {
    int borderId = -1;
    int HEIGHT = 10;
    int WIDTH = 20;
    char BLOCK = '□';
    List<List<Cell>> cells = new ArrayList<>();



    public void initialize(){
        for(int i = 0; i < HEIGHT; i++){
            List<Cell> row = new ArrayList<>();
            for(int j = 0; j < WIDTH; j++){
                row.add(new Cell(i,j));
            }
            cells.add(row);
        }
        this.addBorders();
    }

    private void addBorders(){

        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                boolean isWidthBorder = (j==0||j==WIDTH-1);
                boolean isHeightBorder = (i==0||i==HEIGHT-1);
                if(isWidthBorder || isHeightBorder){
                    cells.get(i).get(j).setId(borderId);
                }
            }
        }


    }

    public void print(){

       for(List<Cell> row:cells){
           for(Cell cell:row){
               if(cell.getId()==borderId){
                   System.out.print(BLOCK+" ");
               } else{
                   System.out.print(cell.getId() + " " );
               }
           }
           System.out.println();

       }


    }
    public void fillWithPairs(){
        List<Cell> fillableCells = new ArrayList<>();
        for (int i = 0;i<HEIGHT;i++){
            for(int j = 0;j < WIDTH;j++){
                Cell c = cells.get(i).get(j);
                if(c.id!=-1){
                    fillableCells.add(c);
                }
            }
        }

        if(fillableCells.size()%2!=0){
            throw new IllegalArgumentException("Odd number of cells");
        }
        Collections.shuffle(fillableCells);
        int pairId = 1;
        for(int i = 0;i <fillableCells.size();i+=2){
            fillableCells.get(i).id = pairId;
            fillableCells.get(i+1).id = pairId;
            pairId++;
            }
        }
    }



