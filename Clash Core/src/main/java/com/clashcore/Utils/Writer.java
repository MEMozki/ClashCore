package Utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import Logic.Device;

public abstract class Writer {

    protected ByteArrayOutputStream buffer;
    protected Device device; 
    public int id;
    public Integer version; 

    public Writer(Device device) {
        this.device = device;
        this.buffer = new ByteArrayOutputStream();
    }

    public void writeInt(int data) {
        writeInt(data, 4);
    }

    public void writeInt(int data, int length) {
        byte[] bytes = new byte[length];
        for (int i = length - 1; i >= 0; i--) {
            bytes[i] = (byte) (data & 0xFF);
            data >>= 8;
        }
        buffer.write(bytes, 0, bytes.length);
    }

    public void writeByte(int data) {
        writeInt(data, 1);
    }

    public void writeString(String data) {
        if (data != null) {
            byte[] strBytes = data.getBytes(StandardCharsets.UTF_8);
            writeInt(strBytes.length);  
            buffer.write(strBytes, 0, strBytes.length);
        } else {
            writeInt(0xFFFFFFFF);
        }
    }

    public abstract void encode();

    public void send() {
        encode();
        if (version != null) {
            device.sendData(id, buffer.toByteArray(), version);
        } else {
            device.sendData(id, buffer.toByteArray());
        }
    }
    
              }
