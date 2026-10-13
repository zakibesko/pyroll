import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ObfuscatedInputStream
        extends FilterInputStream {

    public ObfuscatedInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {

        int b = in.read();

        if (b == -1) {
            return -1;
        }

        return b ^ 0xFF;
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {

        int bytesRead =
                in.read(b, off, len);

        if (bytesRead == -1) {
            return -1;
        }

        for (int i = off;
             i < off + bytesRead;
             i++) {

            b[i] =
                    (byte)(b[i] ^ 0xFF);
        }

        return bytesRead;
    }
}