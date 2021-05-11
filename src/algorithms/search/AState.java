package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {
    private int cost;
    private AState prev;

    public AState(int cost,AState origin) {
        this.cost = cost;
        this.prev = origin;
    }
    public AState(AState origin) {
        this.cost = 0;
        this.prev = origin;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getPrev() {
        return prev;
    }

    public void setPrev(AState prev) {
        this.prev = prev;
    }
}
