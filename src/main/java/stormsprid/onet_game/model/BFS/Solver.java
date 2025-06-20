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


        public boolean perform(Cell start, Cell finish) {
            if (areAdjacent(start, finish)) {
                return true;
            }

            boolean[][][] visited = new boolean[board.getHEIGHT()][board.getWIDTH()][4];

            // Инициализируем очередь с 4 направлениями от стартовой ячейки
            for (Direction dir : Direction.values()) {
                Cell next = dir.move(start, board);
                if (next != null && (next.getId() == -1 || next.equals(finish))) {
                    queue.add(new State(next.getX(), next.getY(), dir, 0));
                }
            }

            while (!queue.isEmpty()) {
                State state = queue.poll();
                int x = state.x;
                int y = state.y;
                Direction dir = state.dir;
                int turns = state.turns;

                if (turns > 2) continue;
                if (x < 0 || x >= board.getWIDTH() || y < 0 || y >= board.getHEIGHT()) continue;
                if (visited[y][x][dir.ordinal()]) continue;

                visited[y][x][dir.ordinal()] = true;

                Cell current = board.getCell(x, y);

                // ✅ ВАЖНО: если добрались до finish, значит соединение возможно
                if (current.equals(finish)) {
                    return true;
                }

                for (Direction newDir : Direction.values()) {
                    int newTurns = (newDir == dir) ? turns : turns + 1;
                    Cell next = newDir.move(current, board);

                    if (next != null && (next.getId() == -1 || next.equals(finish))) {
                        queue.add(new State(next.getX(), next.getY(), newDir, newTurns));
                    }
                }
            }

// ❌ Если ничего не нашли
            return false;
        }
        private boolean areAdjacent(Cell a, Cell b) {
            int dx = Math.abs(a.getX() - b.getX());
            int dy = Math.abs(a.getY() - b.getY());

            return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
        }
        }
