package JUnit;

import algorithms.search.BestFirstSearch;
import algorithms.search.Solution;
import static org.junit.jupiter.api.Assertions.*;


class BestFirstSearchTest {
    BestFirstSearch best;

    BestFirstSearchTest(){
        best = new BestFirstSearch();
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("BestFirstSearch", best.getName());
    }

    @org.junit.jupiter.api.Test
    void getNumberOfNodesEvaluated() {
        assertEquals(0, best.getNumberOfNodesEvaluated());
    }

    @org.junit.jupiter.api.Test
    void solve() {
        Solution solution = best.solve(null);
        if (solution!=null)
            System.out.println("Solution should be null");
    }
}
