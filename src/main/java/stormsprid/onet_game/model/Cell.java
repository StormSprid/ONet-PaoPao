package stormsprid.onet_game.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Cell {
    int x,y;
    int id;

    public Cell(int x, int y ){
        this.x = x;
        this.y = y;
        this.id = 1;
    }
//    final Image image;
//
//    public Cell(Image image) {
//        this.image = image;
//    }


    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", y=" + y +
                ", x=" + x +
                '}';
    }
}
