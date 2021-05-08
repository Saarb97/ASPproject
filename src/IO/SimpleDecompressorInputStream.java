package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    /*public void read(ArrayList<Byte> mazeData) {
        ArrayList<Byte> array = new ArrayList<>();

        for(int i=0;i< mazeData.size();i++)
                writeToArrXTimes(mazeData.get(i),(byte)(i % 2),array );


    }*/


    public int read(byte[] decompressedMaze) throws IOException {
        try {
            ArrayList<Byte> array = new ArrayList<>();
            byte[] mazeData = new byte[decompressedMaze.length];
            in.read(mazeData); //reading compressed maze data from the input stream

            for (int i = 0; i < 12; i++) //adding basic maze info (12 bytes) to the decompressed array (as it is being worked on elsewhere)
                array.add(mazeData[i]);

            for (int i = 12; i < mazeData.length; i++)
                writeToArrXTimes(mazeData[i], (byte) (i % 2), array);

            for(int i = 0;i <array.size();i++) {
                decompressedMaze[i] = array.get(i);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private void writeToArrXTimes(byte x,byte num,ArrayList<Byte> targetArr) {
        for (int i=0 ; i<x ; i++)
            targetArr.add(num);
    }

}
