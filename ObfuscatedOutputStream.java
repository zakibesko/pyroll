import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ObfuscatedOutputStream
        extends FilterOutputStream {

    public ObfuscatedOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(int b) throws IOException {

        out.write(b ^ 0xFF);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {

        byte[] temp = new byte[len];

        for (int i = 0; i < len; i++) {

            temp[i] =
                    (byte)(b[off + i] ^ 0xFF);
        }

        out.write(temp, 0, len);
    }
}