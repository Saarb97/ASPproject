package algorithms.mazeGenerators;


public class EmptyMazeGenerator extends AMazeGenerator {

    public EmptyMazeGenerator() {}


    @Override
    public Maze generate(int rows, int columns) {
        // TODO ERROR HANDLING
        // min size 2X2
        return new Maze(rows,columns);
    }
}
