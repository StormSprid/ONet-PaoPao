    package stormsprid.onet_game.model.BFS;

    import stormsprid.onet_game.model.Board;
    import stormsprid.onet_game.model.Cell;

    public enum Direction {
        UP,DOWN,LEFT,RIGHT;


        public Cell move(Cell a,Board board){
            int newX = a.getX();
            int newY = a.getY();
            switch (this){
                case UP ->newY--;
                case DOWN -> newY++;
                case LEFT -> newX--;
                case RIGHT -> newX++;


            }
            if (newX<0 || newY<0 || newX >= board.getWIDTH()|| newY >= board.getHEIGHT()){
                return null;
            }
            return board.getCell(newX,newY);
        }
    }
