package stormsprid.onet_game.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class Cell {
    int x,y;
    int id;

    public Cell(int x, int y ){
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, id);
    }
}
