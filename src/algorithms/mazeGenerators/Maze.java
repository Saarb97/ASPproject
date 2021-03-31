package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private Position end;
    private int[][] maze;
    public Maze(Position start,Position end,int[][] maze) {
        this.start = start;
        this.end = end;
        this.maze = maze;
    }
    public void print() {
        for (int i = 0; i <= maze.length - 1; i++) {
            System.out.println("{ ");
            for (int j = 0; j <= maze[i].length-1; j++) {
                if (start.getRowIndex() == i & start.getColumnIndex() == j)
                    System.out.print("S ");
                else if (end.getRowIndex() == i & end.getColumnIndex() == j)
                    System.out.print("E ");
                else
                    System.out.print(maze[i][j]);
            }
            System.out.println("}");
        }
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return end;
    }
}
