package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * Get the maze Parameters and return a compress maze to client.
     * @param fromClient
     * @param toClient
     * @throws IOException
     */
    @Override
    public void serverStrategy(InputStream fromClient, OutputStream toClient) throws IOException {
        try{
            ObjectInputStream inClient = new ObjectInputStream(fromClient);
            ObjectOutputStream outClient = new ObjectOutputStream(toClient);
            ByteArrayOutputStream ByteArrayToClient = new ByteArrayOutputStream();
            MyCompressorOutputStream compress = new MyCompressorOutputStream(ByteArrayToClient);
            int[] mazeSize = (int[])inClient.readObject();
            AMazeGenerator mazeGenerator = Configurations.getMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]);
            compress.write(maze.toByteArray());
            outClient.writeObject(ByteArrayToClient.toByteArray());
            outClient.flush();
            outClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}