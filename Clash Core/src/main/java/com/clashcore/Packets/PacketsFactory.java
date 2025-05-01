package Packets.Factory;

import java.util.HashMap;
import java.util.Map;
import Packets.Messages.Client.Login;
import Packets.Messages.Client.KeepAlive;
import Logic.Device;

public class PacketsFactory {
    public static Map<Integer, PacketFactory> availablePackets = new HashMap<>();

    static {
        availablePackets.put(10101, new PacketFactory() {
            @Override
            public Packet createInstance(byte[] data, Device device) {
                return new Login(data, device);
            }
        });
        availablePackets.put(10108, new PacketFactory() {
            @Override
            public Packet createInstance(byte[] data, Device device) {
                return new KeepAlive(data, device);
            }
        });
    }
}