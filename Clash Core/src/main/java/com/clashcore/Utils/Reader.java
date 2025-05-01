package Utils;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class Reader {
    private ByteArrayInputStream stream;

    public Reader(byte[] data) {
        this.stream = new ByteArrayInputStream(data);
    }

    public int readByte() throws IOException {
        int value = stream.read();
        if (value == -1) {
            throw new EOFException();
        }
        return value;
    }

    public boolean readBool() throws IOException {
        return readByte() != 0;
    }

    public int readUint16() throws IOException {
        byte[] bytes = new byte[2];
        if (stream.read(bytes) != 2)
            throw new EOFException();
        return ByteBuffer.wrap(bytes).getShort() & 0xFFFF;
    }

    public int readUint32() throws IOException {
        byte[] bytes = new byte[4];
        if (stream.read(bytes) != 4)
            throw new EOFException();
        return ByteBuffer.wrap(bytes).getInt();
    }

    private int readVarInt(boolean isRr) throws IOException {
        int shift = 0;
        int result = 0;
        while (true) {
            byte[] one = new byte[1];
            if (stream.read(one) != 1)
                throw new EOFException();
            if (isRr && shift == 0) {
                one = sevenBitRotateLeft(one);
            }
            int i = one[0] & 0xFF;
            result |= (i & 0x7f) << shift;
            shift += 7;
            if ((i & 0x80) == 0) {
                break;
            }
        }
        return result;
    }

    public int readInt32() throws IOException {
        return readVarInt(false);
    }

    public int readSint32() throws IOException {
        int n = readVarInt(false);
        return (n >>> 1) ^ (-(n & 1));
    }

    public int readVint() throws IOException {
        int n = readVarInt(true);
        return (n >>> 1) ^ (-(n & 1));
    }

    private byte[] sevenBitRotateLeft(byte[] one) {
        int n = one[0] & 0xFF;
        int seventh = (n & 0x40) >> 6;
        int msb = (n & 0x80) >> 7;
        n = n << 1;
        n = n & ~(0x181);
        n = n | (msb << 7) | seventh;
        return new byte[]{(byte)n};
    }

    public long readLong() throws IOException {
        byte[] bytes = new byte[8];
        if (stream.read(bytes) != 8)
            throw new EOFException();
        return ByteBuffer.wrap(bytes).getLong();
    }

    public String readString() throws IOException {
        int length = readUint32();
        if (length == 0xFFFFFFFF) {
            return "";
        } else {
            byte[] bytes = new byte[length];
            if (stream.read(bytes) != length)
                throw new EOFException("String out of range.");
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    public String readZString() throws IOException, DataFormatException {
        byte[] lenBytes = new byte[4];
        if (stream.read(lenBytes) != 4)
            throw new EOFException();
        int length = ByteBuffer.wrap(lenBytes).getInt();
        if (length == 0xFFFFFFFF) {
            return "";
        }
        byte[] zlenBytes = new byte[4];
        if (stream.read(zlenBytes) != 4)
            throw new EOFException();
        int zlength = ByteBuffer.wrap(zlenBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
        byte[] compressed = new byte[length - 4];
        if (stream.read(compressed) != (length - 4))
            throw new EOFException();
        Inflater inflater = new Inflater();
        inflater.setInput(compressed);
        byte[] result = new byte[zlength];
        int resultLength = inflater.inflate(result);
        inflater.end();
        if (resultLength != zlength) {
            throw new DataFormatException("Decompress error: incorrect length");
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    public int peekInt(int length) throws IOException {
        stream.mark(length);
        byte[] bytes = new byte[length];
        if (stream.read(bytes) != length)
            throw new EOFException();
        stream.reset();
        int value = 0;
        for (int i = 0; i < length; i++) {
            value = (value << 8) | (bytes[i] & 0xFF);
        }
        return value;
    }

    public String readHexa(int length) throws IOException {
        byte[] bytes = new byte[length];
        if (stream.read(bytes) != length)
            throw new EOFException();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
