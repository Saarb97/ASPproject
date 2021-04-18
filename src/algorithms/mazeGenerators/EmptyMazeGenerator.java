package algorithms.mazeGenerators;


public class EmptyMazeGenerator extends AMazeGenerator {

    public EmptyMazeGenerator() {}


    @Override
    public Maze generate(int rows, int columns) {
        if (rows >= 2 && columns >= 2)
            return new Maze(rows,columns);
        return null;
    }
}
