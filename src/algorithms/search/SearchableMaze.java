package algorithms.search;

import algorithms.mazeGenerators.*;

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

    public Maze getMaze() {return maze;}

    public ArrayList<AState> getAllSuccessors(AState aState) {

        MazeState state = (MazeState) aState;
        int row = state.getCurrentPos().getRowIndex();
        int col = state.getCurrentPos().getColumnIndex();

        ArrayList<AState> possibleStates = new ArrayList<>();
        if(maze.getValueAtPos(row, col-1) == 0)
            possibleStates.add(new MazeState(state.getCost()+10,state,new Position(row, col-1)));

        if(maze.getValueAtPos(row-1, col+1) == 0 && (maze.getValueAtPos(row-1,col) == 0 || maze.getValueAtPos(row, col+1) == 0))
            possibleStates.add(new MazeState(state.getCost()+15,state,new Position(row-1, col+1)));

        if(maze.getValueAtPos(row, col+1) == 0)
            possibleStates.add(new MazeState(state.getCost()+10,state,new Position(row, col+1)));

        if(maze.getValueAtPos(row+1, col+1) == 0 && (maze.getValueAtPos(row+1, col) == 0 || maze.getValueAtPos(row, col+1) == 0 ) )
            possibleStates.add(new MazeState(state.getCost()+15,state,new Position(row+1, col+1)));

        if(maze.getValueAtPos(row+1, col) == 0)
            possibleStates.add(new MazeState(state.getCost()+10,state,new Position(row+1, col)));

        if(maze.getValueAtPos(row+1, col-1) == 0 && (maze.getValueAtPos(row, col-1) == 0 || maze.getValueAtPos(row+1, col) == 0 ))
            possibleStates.add(new MazeState(state.getCost()+15,state,new Position(row+1, col-1)));

        if(maze.getValueAtPos(row-1,col) == 0 )
            possibleStates.add(new MazeState(state.getCost()+10,state,new Position(row-1, col)));

        if(maze.getValueAtPos(row-1, col-1) == 0 && (maze.getValueAtPos(row, col-1) == 0 || maze.getValueAtPos(row-1,col) == 0))
            possibleStates.add(new MazeState(state.getCost()+15,state,new Position(row-1, col-1)));

        return possibleStates;
    }
}
