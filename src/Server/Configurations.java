package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.search.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    static Properties proper;
    private static Configurations config = null;

    private static ISearchingAlgorithm SOLVER_ALGORITHM = null;
    private static AMazeGenerator MAZE_GENERATOR = null;
    private static int numThreads;

    public void setSolverAlgorithm(ISearchingAlgorithm solverAlgorithm) {
        SOLVER_ALGORITHM = solverAlgorithm;
    }

    public void setMazeGenerator(AMazeGenerator mazeGenerator)
    {
        MAZE_GENERATOR = mazeGenerator;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(String numThreads) {
        Configurations.numThreads = Integer.parseInt(numThreads);
    }

    private Configurations(){
        proper = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            proper.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s1 = proper.getProperty("mazeSearchingAlgorithm");
        switch (s1.toLowerCase()){
            case "MyMazeGenerator" :
                MAZE_GENERATOR = new MyMazeGenerator();

            case "SimpleMazeGenerator" :
                MAZE_GENERATOR = new SimpleMazeGenerator();

            default:
                MAZE_GENERATOR = new EmptyMazeGenerator();
        }

        String s2 = proper.getProperty("mazeGeneratingAlgorithm");
        switch (s2.toLowerCase()){
            case "BestFirstSearch" :
                SOLVER_ALGORITHM = new BestFirstSearch();

            case "BreadthFirstSearch" :
                SOLVER_ALGORITHM = new BreadthFirstSearch();

            default:
                SOLVER_ALGORITHM = new DepthFirstSearch();
        }
        numThreads = Integer.parseInt(proper.getProperty("threadPoolSize"));
    }

    public static Configurations getInstance(){
        if(config == null)
            config = new Configurations();
        return config;
    }



    public static AMazeGenerator getMazeGenerator() {
        String generator = proper.getProperty("mazeGeneratingAlgorithm");
        switch (generator.toLowerCase()){
            case "MyMazeGenerator" :
                return new MyMazeGenerator();

            case "SimpleMazeGenerator" :
                return new SimpleMazeGenerator();

            default:
                return new EmptyMazeGenerator();
        }
    }

    public static ASearchingAlgorithm getSearchingAlgorithm(){
        String algo = proper.getProperty("mazeSearchingAlgorithm");
        switch (algo.toLowerCase()){
            case "BestFirstSearch" :
                return new BestFirstSearch();

            case "BreadthFirstSearch" :
                return new BreadthFirstSearch();

            default:
                return new DepthFirstSearch();
        }
    }
}