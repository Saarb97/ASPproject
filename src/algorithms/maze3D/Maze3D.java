package algorithms.maze3D;

import java.util.concurrent.ThreadLocalRandom;


public class Maze3D {
    private Position3D start;
    private Position3D end;
    private int[][][] maze;
    public Maze3D(Position3D start, Position3D end, int[][][] maze) {
        if(start != null && end != null && maze != null) {
            this.start = start;
            this.end = end;
            this.maze = maze;
        }
    }

    public Maze3D(int depth, int rows, int columns) {
        if(depth >= 1 && rows >= 2 && columns >= 2)
            this.maze = new int[depth][rows][columns];
    }


    public void print() {
        System.out.println("{");
        for(int depth = 0; depth < maze.length; depth++){
            for(int row = 0; row < maze[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < maze[0][0].length; col++) {
                    if (depth == start.getDepthIndex() && row == start.getRowIndex() && col == start.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == end.getDepthIndex() && row == end.getRowIndex() && col == end.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(maze[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < maze.length - 1) {
                System.out.print("---");
                for (int i = 0; i < maze[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    public Position3D getStartPosition() {
        return new Position3D(start);
    }

    public Position3D getGoalPosition() {
        return new Position3D(end);
    }

    public Position3D RandomPositionOnEdge(int depthIn, int rows,int columns) {
        int depth = ThreadLocalRandom.current().nextInt(0, depthIn);
        int row = ThreadLocalRandom.current().nextInt(0, rows);
        int column = ThreadLocalRandom.current().nextInt(0, columns);

        return null;
    }

    public void SetPos (int k, int i, int j,int value)  {
        if(k >= 0 && k <= maze.length-1 && i >= 0 && i <= maze[0].length-1 && j >= 0 && j <= maze[0][0].length-1 && ((value == 0) || (value == 1)))
            maze[k][i][j] = value;
    }
    public void SetPos (Position3D pos,int value)  {
        if(pos != null && ((value == 1) || (value == 0)))
            maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()] = value;
    }
    public int getValueAtPos(int depth, int row,int col) {
        if (depth >= maze.length || row >= maze[0].length || col >= maze[0][0].length || row < 0 || col < 0 || depth <0)
            return 1;
        return maze[depth][row][col];
    }

    public int[][][] getMaze() {
        return this.maze;
    }
}
