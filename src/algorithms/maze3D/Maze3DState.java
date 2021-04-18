package algorithms.maze3D;

import algorithms.search.AState;

import java.util.Objects;

public class Maze3DState extends AState {

    private Position3D currentPos;

    public Maze3DState(int cost, AState origin, Position3D currentPos) {
        super(cost, origin);
        this.currentPos = new Position3D(currentPos); // might not need to duplicate
    }

    public Maze3DState(AState origin, Position3D currentPos) {
        super(origin);
        this.currentPos = currentPos; // might need to duplicate
    }

    @Override
    public boolean equals(Object o) { // will need updating
        if (this == o) return true;
        if (!(o instanceof Maze3DState)) return false;
        Maze3DState mazeState = (Maze3DState) o;
        return currentPos.equals(mazeState.currentPos);
    }

    @Override
    public int hashCode() { // will need updating
        return Objects.hash(currentPos);
    }

    public Position3D getCurrentPos() {
        return currentPos;
    }

    @Override
    public String toString() {
        return currentPos.toString();
    }
}
