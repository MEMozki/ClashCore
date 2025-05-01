package Logic;

import CryptoRC4.CryptoRc4;
import Packets.Factory.Packet;
import Packets.Factory.PacketsFactory;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Device {
    public String AndroidID;
    public String DeviceModel;
    public String OpenUDID;
    public String OSVersion;
    public boolean IsAndroid;
    public String Language;

    private Socket socket;
    private CryptoRc4 crypto;

    public Device(Socket socket) {
        this.socket = socket;
        this.crypto = new CryptoRc4();
    }

    public void sendData(int id, byte[] data, Integer version) {
        byte[] encrypted = crypto.encrypt(data);
        byte[] packetID = toBytes(id, 2);
        byte[] packetVersion = (version != null) ? toBytes(version, 2) : toBytes(0, 2);
        byte[] lengthBytes = toBytes(encrypted.length, 3);
        byte[] packet = concat(packetID, lengthBytes, packetVersion, encrypted);

        try {
            if (socket == null) {
            } else {
                OutputStream out = socket.getOutputStream();
                out.write(packet);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[*] " + id + " sent");
    }

    public void sendData(int id, byte[] data) {
        sendData(id, data, null);
    }

    public byte[] decrypt(byte[] data) {
        return crypto.decrypt(data);
    }

    public void processPacket(int packetID, byte[] payload) {
        System.out.println("[*] " + packetID + " received");
        try {
            byte[] decrypted = decrypt(payload);
            if (PacketsFactory.availablePackets.containsKey(packetID)) {
                Packet message = PacketsFactory.availablePackets.get(packetID).createInstance(decrypted, this);
                message.decode();
                message.process();
            } else {
                System.out.println("[*] " + packetID + " not handled");
            }
        } catch (Exception e) {
            System.out.println("[*] Error while decrypting / handling " + packetID);
            e.printStackTrace();
        }
    }

    public static byte[] toBytes(int value, int length) {
        byte[] bytes = new byte[length];
        for (int i = length - 1; i >= 0; i--) {
            bytes[i] = (byte)(value & 0xFF);
            value >>= 8;
        }
        return bytes;
    }

    public static byte[] concat(byte[]... arrays) {
        int totalLength = 0;
        for (byte[] arr : arrays) {
            totalLength += arr.length;
        }
        byte[] result = new byte[totalLength];
        int currentPos = 0;
        for (byte[] arr : arrays) {
            System.arraycopy(arr, 0, result, currentPos, arr.length);
            currentPos += arr.length;
        }
        return result;
    }
}
