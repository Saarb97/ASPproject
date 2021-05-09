package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;
    public MyCompressorOutputStream(OutputStream out) {
        if(out != null)
            this.out = out;
    }

    @Override
    public void write(int i) throws IOException { }

    @Override
    public void write(byte[] b) throws IOException {
        try {
            ArrayList<Byte> array = new ArrayList();

            for(int i = 0; i<12;i++) // adds first 12 bytes for 6 basic maze info (rows,cols,startpos,endpos)
                array.add(b[i]);

            int byteSize = 0;
            byte currentByte = 0;

            // the algorithm merges the value of each 8 bytes into 8 bits (because are values are 0 or 1 we can store them in a single bit)
            // in our case of maze it should be more space efficient than the other algorithm
            // because testing shows most of the time there are iterating 1 and 0's in the maze (or some couples or triplets of the same bit)
            // so we can't take much advantage of counting how many times a bit is repeating.
            //best case scenario when we encounter 8 maze cells 0,1,0,1,0,1,0,1, the simple compression will create 8 bytes (1,1,1,1,1,1,1,1)
            // and this compression will create a single byte (01010101).
            for(int i=12; i<b.length;i++) {
                //if the current byte is full, add it to the array and start a new byte
                if(byteSize == 8) {
                    byteSize = 0;
                    array.add(currentByte);
                    currentByte = 0;
                }
                //add the value of b[i] to the current byte at a specific bit (using addition of 2^n to the byte value)
                currentByte += b[i] * Math.pow(2,byteSize++);
            }
            //adding last byte
            array.add(currentByte);

            //converting arrayList to normal array for return
            byte[] finalArray = new byte[array.size()];
            for(int i=0;i<array.size();i++)
                finalArray[i] = array.get(i);

            out.write(finalArray);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
