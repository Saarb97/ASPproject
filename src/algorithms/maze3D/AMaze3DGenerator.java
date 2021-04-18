package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {

    public abstract Maze3D generate(int depth, int rows, int columns);

    public long measureAlgorithmTimeMillis(int depth, int rows, int columns) {
        if(depth >= 1 && rows >= 2 && columns >= 2) {
            long startTime = System.currentTimeMillis();
            Maze3D maze = generate(depth, rows, columns);
            long finishTime = System.currentTimeMillis();
            return finishTime - startTime;
        }
        return 0;
    };
}
