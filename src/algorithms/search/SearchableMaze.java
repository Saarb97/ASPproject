package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return new MazeState(null,maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(null, maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return null;
    }
}
