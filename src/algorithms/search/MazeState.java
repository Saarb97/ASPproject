package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

public class MazeState extends AState implements Serializable {

    private Position currentPos;

    public MazeState(int cost, AState origin, Position currentPos) {
        super(cost, origin);
        this.currentPos = new Position(currentPos); // might not need to duplicate
    }

    public MazeState(AState origin, Position currentPos) {
        super(origin);
        this.currentPos = currentPos; // might need to duplicate
    }

    @Override
    public boolean equals(Object o) { // will need updating
        if (this == o) return true;
        if (!(o instanceof MazeState)) return false;
        MazeState mazeState = (MazeState) o;
        return currentPos.equals(mazeState.currentPos);
    }

    @Override
    public int hashCode() { // will need updating
        return Objects.hash(currentPos);
    }

    public Position getCurrentPos() {
        return currentPos;
    }

    @Override
    public String toString() {
        return currentPos.toString();
    }
}
