package algorithms.maze3D;



import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {

    private Maze3D maze;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return new Maze3DState(null,maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new Maze3DState(null, maze.getGoalPosition());
    }

    public Maze3D getMaze() {return maze;}

    public ArrayList<AState> getAllSuccessors(AState aState) {
        if(aState == null){return null;}
        Maze3DState state = (Maze3DState) aState;
        int depth = state.getCurrentPos().getDepthIndex();
        int row = state.getCurrentPos().getRowIndex();
        int col = state.getCurrentPos().getColumnIndex();

        ArrayList<AState> possibleStates = new ArrayList<>();

        if(maze.getValueAtPos(depth-1,row, col) == 0)
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth-1,row, col)));

        if(maze.getValueAtPos(depth,row, col-1) == 0)
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth,row, col-1)));

        if(maze.getValueAtPos(depth,row, col+1) == 0)
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth,row, col+1)));
        
        if(maze.getValueAtPos(depth,row+1, col) == 0)
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth,row+1, col)));

        if(maze.getValueAtPos(depth,row-1,col) == 0 )
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth,row-1, col)));

        if(maze.getValueAtPos(depth+1,row, col) == 0)
            possibleStates.add(new Maze3DState(state.getCost()+10,state,new Position3D(depth+1,row, col)));

        return possibleStates;
    }
}
