package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }


    public int read(byte[] decompressedMaze) throws IOException {
        try {
            ArrayList<Byte> array = new ArrayList<>();
            byte[] mazeData = new byte[decompressedMaze.length];
            in.read(mazeData); //reading compressed maze data from the input stream

            for (int i = 0; i < 12; i++) //adding basic maze info (12 bytes) to the decompressed array (as it is being worked on elsewhere)
                array.add(mazeData[i]);

            //noting the size of the maze to avoid adding too many bits to the maze.
            // if the last bit wasn't full in the compression, it will have extra 0's
            int rows = ((array.get(0) & 0xFF) << 8) | (array.get(1) & 0xFF);
            int cols = ((array.get(2) & 0xFF) << 8) | (array.get(3) & 0xFF);
            int totalCells = rows*cols;
            int cellsAdded = 0;

            for (int i = 12; i < mazeData.length; i++) { //using bit operations to extract the value of each bit
                for (int j=0;j<8;j++) {
                    if(cellsAdded < totalCells) {
                        array.add((byte)((mazeData[i] >> j) & 1));
                        cellsAdded++;
                    }
                }
            }
            for(int i = 0;i <array.size();i++) {
                decompressedMaze[i] = array.get(i);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /*byte test = (byte)105;
        int pos = 0;
        System.out.println((test >> pos) & 1);*/

}
