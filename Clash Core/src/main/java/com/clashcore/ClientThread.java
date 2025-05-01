package com.clashcore;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import Logic.Device;
import Packets.Factory.Packet;
import Packets.Factory.PacketsFactory;

public class ClientThread extends Thread {
    private Socket client;
    private Device device;

    public ClientThread(Socket client) {
        this.client = client;
        this.device = new Device(client);
    }

    private byte[] recvall(InputStream in, int size) throws IOException {
        byte[] data = new byte[size];
        int totalRead = 0;
        while (totalRead < size) {
            int read = in.read(data, totalRead, size - totalRead);
            if (read == -1) {
                throw new EOFException();
            }
            totalRead += read;
        }
        return data;
    }

    public void run() {
        try {
            InputStream in = client.getInputStream();
            while (true) {
                byte[] header = recvall(in, 7);
                int packetID = ((header[0] & 0xFF) << 8) | (header[1] & 0xFF);
                int length = ((header[2] & 0xFF) << 16) | ((header[3] & 0xFF) << 8) | (header[4] & 0xFF);
                int version = ((header[5] & 0xFF) << 8) | (header[6] & 0xFF);
                byte[] data = recvall(in, length);
                if (header.length >= 7) {
                    if (length == data.length) {
                        System.out.println("[*] " + packetID + " received");
                        try {
                            byte[] decrypted = device.decrypt(data);
                            if (PacketsFactory.availablePackets.containsKey(packetID)) {
                                Packet message = PacketsFactory.availablePackets.get(packetID).createInstance(decrypted, device);
                                message.decode();
                                message.process();
                            } else {
                                System.out.println("[*] " + packetID + " not handled");
                            }
                        } catch (Exception e) {
                            System.out.println("[*] Error while decrypting / handling " + packetID);
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("[*] Incorrect Length for packet " + packetID + " (header length: " 
                            + length + ", data length: " + data.length + ")");
                    }
                } else {
                    System.out.println("[*] Received an invalid packet from client");
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
