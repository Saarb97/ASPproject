package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        super();
        Comparator<AState> comparator = new Comparator<AState>() {
            @Override
            public int compare(AState op_1, AState op_2) {
                if(op_1.getCost() > op_2.getCost())
                    return 1;
                if(op_1.getCost() < op_2.getCost())
                    return -1;
                return 0;
            }
        };
        visited = new PriorityQueue<AState>(comparator);
    }
}