package IO;

import java.io.IOException;
import java.io.OutputStream;

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

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
