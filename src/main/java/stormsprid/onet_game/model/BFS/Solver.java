    package stormsprid.onet_game.model.BFS;

    import stormsprid.onet_game.model.Board;
    import stormsprid.onet_game.model.Cell;

    import java.util.LinkedList;
    import java.util.PriorityQueue;
    import java.util.Queue;

    public class Solver {
        Queue<State> queue = new LinkedList<>();
        private Board board;
        public Solver(Board board) {
            this.board = board;
        }

        public void initialize(Cell a){
            queue.add(new State(a.getX(),a.getY(),Direction.UP,0));
            queue.add(new State(a.getX(),a.getY(),Direction.RIGHT,0));
            queue.add(new State(a.getX(),a.getY(),Direction.DOWN,0));
            queue.add(new State(a.getX(),a.getY(),Direction.LEFT,0));

        }
        public void perform(Cell finish) {
            System.out.println("Start searching>>>");
            System.out.println("Finish: " + finish.getX() + " " + finish.getY());

            while (!queue.isEmpty()) {
                State state = queue.poll();
                Cell current = new Cell(state.x, state.y);
                System.out.println("Current Cell: " + current.getX() + " " + current.getY());

                Cell next = state.dir.move(current, board);

                while (next != null) {
                    // ✅ Проверка цели — вне зависимости от id
                    if (next.equals(finish)) {
                        System.out.println("Solution found!");
                        return;
                    }

                    // Двигаемся только по пустым клеткам
                    if (next.getId() != -1) break;

                    queue.add(new State(next.getX(), next.getY(), state.dir, state.turns));
                    System.out.println("Move To: " + next.getX() + " " + next.getY());

                    next = state.dir.move(next, board);
                }
            }

            System.out.println("No solution found.");
        }


    }
