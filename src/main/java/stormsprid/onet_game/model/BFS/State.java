package stormsprid.onet_game.model.BFS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class State {
    int y,x;
    Direction dir;
    int turns;


}
