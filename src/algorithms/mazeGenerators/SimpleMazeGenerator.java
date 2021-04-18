package algorithms.mazeGenerators;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        if (rows >= 2 && columns >= 2) {
            Maze maze = new Maze(rows, columns);
            for (int i = 0; i <= rows - 1; i++) {
                for (int j = 0; j <= columns - 1; j++) {
                    maze.SetPos(i, j, ThreadLocalRandom.current().nextInt(0, 2));
                }
            }


            Position pos = maze.getStartPosition();
            int rand;
            while (!pos.equals(maze.getGoalPosition())) {
                rand = ThreadLocalRandom.current().nextInt(0, 2); // algo creates different shapes of paths.
                // random selection between horizontal and vertical pathing.
                if (rand == 0 && pos.getRowIndex() != maze.getGoalPosition().getRowIndex()) {
                    if (pos.getRowIndex() > maze.getGoalPosition().getRowIndex())
                        pos.setRow(pos.getRowIndex() - 1);
                    else if (pos.getRowIndex() < maze.getGoalPosition().getRowIndex())
                        pos.setRow(pos.getRowIndex() + 1);
                } else if (pos.getColumnIndex() != maze.getGoalPosition().getColumnIndex()) {
                    if (pos.getColumnIndex() > maze.getGoalPosition().getColumnIndex())
                        pos.setColumn(pos.getColumnIndex() - 1);
                    else if (pos.getColumnIndex() < maze.getGoalPosition().getColumnIndex())
                        pos.setColumn(pos.getColumnIndex() + 1);
                }
                maze.SetPos(pos, 0);
            }
            return maze;
        }
        return null;
    }
}
