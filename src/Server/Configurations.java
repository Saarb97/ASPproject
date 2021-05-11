package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.search.*;

import java.util.Locale;
import java.util.Properties;

public class Configurations {

    static Properties proper;

    private static final String SOLVER_ALGORITHM = "Solver Algorithm";
    private static final String MAZE_GENERATOR = "Maze Generator";

    public static AMazeGenerator getMazeGenerator() {
        String generator = proper.getProperty("MazeGenerator");
        switch (generator.toLowerCase()){
            case "mymaze" :
                return new MyMazeGenerator();

            case "simplemaze" :
                return new SimpleMazeGenerator();

            default:
                return new EmptyMazeGenerator();
        }
    }

    public static ASearchingAlgorithm getSearchingAlgorithm(){
        String algo = proper.getProperty("SearchingAlgorithm");
        switch (algo.toLowerCase()){
            case "best first search" :
                return new BestFirstSearch();

            case "breadth first search" :
                return new BreadthFirstSearch();

            default:
                return new DepthFirstSearch();
        }
    }
}