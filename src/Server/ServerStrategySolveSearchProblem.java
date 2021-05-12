package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private String tempPath = System.getProperty("java.io.tmpdir");
    //private String tempPath = System.getProperty("/tmp"); //java.io.tmpdir
    private Lock lock = new ReentrantLock(true);

    /**
     * Write the solution into a file(outstream) and Reading from a file.
     * @param FromClient
     * @param ToClient
     */
    @Override
    public void serverStrategy(InputStream FromClient, OutputStream ToClient)  {
        try {
            // Setting and initial variables.
            Solution sol;
            ObjectInputStream fromClient = new ObjectInputStream(FromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(ToClient);

            Maze maze = (Maze)fromClient.readObject();
            String path = tempPath + "maze" + maze.toString();
            lock.lock();
            File file = new File(path);

            // if solution does not exists already in the folder, solve the maze and send to the client
            if (!file.exists())
            {
                lock.unlock();
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                sol = Configurations.getSearchingAlgorithm().solve(searchableMaze);
                lock.lock();
                FileOutputStream fileOut = new FileOutputStream(path);
                fileOut.flush();
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.flush();
                objectOut.writeObject(sol);
                objectOut.flush();

            }
            // if the maze was already solved, send the solution to the client
            else {
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                sol = (Solution) objectIn.readObject();
                fileIn.close();
                objectIn.close();
            }
            lock.unlock();
            toClient.writeObject(sol);
            toClient.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}