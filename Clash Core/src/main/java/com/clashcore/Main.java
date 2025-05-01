package com.clashcore;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Logic.Device;

public class Main {
    public static void main(String[] args) {
        ServerThread server = new ServerThread("0.0.0.0", 9339);
        server.start();
    }
}
