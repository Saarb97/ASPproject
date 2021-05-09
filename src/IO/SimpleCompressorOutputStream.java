package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {

    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(byte[] b) throws IOException {

        ArrayList<Byte> array = new ArrayList();
        byte currentCount = 0;
        try {

            for(int i = 0; i<12;i++) // adds first 12 bytes for 6 basic maze info (rows,cols,startpos,endpos)
                array.add(b[i]);

            //now we add the maze itself
            if ((b[12] != 0)) { //we start the array with 0's. if first byte not 0, we insert 0 to the array.
                array.add((byte) 0);
            }
            currentCount++; //if first byte is 0 or 1, we add 1 to the count of the current byte type
            for(int i=13; i<b.length;i++) {
                if (b[i - 1] == b[i]) {
                    if (currentCount == -1) { //checking if reached max size of byte (-1 signed, 255 unsigned)
                        array.add(currentCount);
                        array.add((byte) 0);
                        currentCount = 1;
                    }
                    currentCount++;
                }
                else {
                    array.add(currentCount);
                    currentCount = 1;
                }
            }
            array.add(currentCount);
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
    @Override
    public void write(int i) throws IOException {}

}
